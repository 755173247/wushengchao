package com.sdc.factor.entity.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sdc.factor.entity.common.beans.CommonHttpProxyProperties;
import com.sdc.factor.entity.common.constants.WebConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用使用代理请求第三方API的客户端
 *
 * @author Sean
 * @since 2019-03-30
 */
public abstract class AbstractHttpClient implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpClient.class);

    /**
     * json处理器
     */
    protected ObjectMapper objectMapper;

    /**
     * 通用代理
     */
    protected Proxy proxy;

    /**
     * 公共代理配置
     */
    protected CommonHttpProxyProperties commonHttpProxyProperties;

    /**
     * 连接属性
     */
    private Map<String, String> commonConnectionProperties = new HashMap<>();

    @Autowired(required = false)
    public void setObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper != null) {
            LOGGER.info("Use autowired object mapper for json processing");
            this.objectMapper = objectMapper;
        } else {
            this.objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .configure(SerializationFeature.INDENT_OUTPUT, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            LOGGER.info("Use new created object mapper for json processing");
        }
    }

    @Autowired(required = false)
    public void setLepinSecurityProperties(CommonHttpProxyProperties commonHttpProxyProperties) {
        if (commonHttpProxyProperties != null) {
            this.commonHttpProxyProperties = commonHttpProxyProperties;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.proxy = this.constructPrivateProxy();
        if (proxy == null) {
            //如果代理为空则检查通用代理是否可用
            if (this.commonHttpProxyProperties != null && StringUtils.isNoneBlank(this.commonHttpProxyProperties.getHost(), String.valueOf(this.commonHttpProxyProperties.getPort()))) {
                this.proxy = this.constructProxy(this.commonHttpProxyProperties.getHost().trim(), this.commonHttpProxyProperties.getPort(), this.commonHttpProxyProperties.getUser(), this.commonHttpProxyProperties.getPassword());
            }
        } else {
            LOGGER.info("Current client use private proxy setting instead of the common proxy setting");
        }
    }

    /**
     * 构建服务自有的代理，如果不使用通用代理则需覆盖此方法
     * @return 代理配置，默认返回null
     */
    protected Proxy constructPrivateProxy() {
        return null;
    }

    /**
     * 构造代理对象
     * @param proxyHost 代理主机
     * @param proxyPort 代理端口
     * @param proxyUser 代理用户
     * @param proxyPassword 用户密码
     * @return 代理对象
     */
    protected Proxy constructProxy(String proxyHost, int proxyPort, String proxyUser, String proxyPassword) {
        if (StringUtils.isNoneBlank(proxyHost, String.valueOf(proxyPort))) {
            InetSocketAddress proxyAddress = new InetSocketAddress(proxyHost.trim(), proxyPort);
            LOGGER.info("Proxy settings are as below: ");
            LOGGER.info("Proxy host: " + proxyHost);
            LOGGER.info("Proxy port: " + proxyPort);
            LOGGER.info("Proxy user: " + proxyUser);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);

            //添加验证信息
            if (StringUtils.isNotBlank(proxyUser)) {
                String headerKey = "Proxy-Authorization";
                String headerValue = "Basic " + Base64.encodeBase64String((proxyUser.trim() + ":" + (StringUtils.isBlank(proxyPassword) ? "" : proxyPassword.trim())).getBytes());
                //将代理的用户名和密码信息设置到请求属性中
                this.commonConnectionProperties.put(headerKey, headerValue);
            }
            return proxy;
        } else {
            return null;
        }
    }

    /**
     * 请求第三方连接并返回指定的结果
     * @param requestUrl 请求地址
     * @param responseType 响应数据类型
     * @param requestParameters 请求参数
     * @param requestBody 请求体，当请求体为空时默认为GET，当请求体不为空时默认为POST
     * @param connectionProperties 连接参数
     * @param <T>
     * @return
     */
    protected <T> T request(RequestMethod requestMethod, Class<T> responseType, String requestUrl, Map<String, String> requestParameters, Object requestBody, Map<String, String> connectionProperties) throws Exception {
        if (StringUtils.isBlank(requestUrl)) {
            LOGGER.error("Cannot request empty url address");
            return null;
        }
        String queryParameters = "";
        if (requestParameters != null) {
            //拼接参数
            queryParameters = requestParameters.entrySet().stream().map(entry -> {
                try {
                    return entry.getKey() + WebConstants.PARAM_VALUE_JOIN + URLEncoder.encode(entry.getValue(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("Fail to encode param due to exception found", e);
                    return null;
                }
            }).reduce((paramA, paramB) -> paramA + WebConstants.PARAM_SPLIT + paramB).orElse("");
        }
        if (StringUtils.isNotBlank(queryParameters)) {
            requestUrl = requestUrl + (requestUrl.indexOf(WebConstants.QUERY_SPLIT) > 0 ? WebConstants.PARAM_SPLIT : WebConstants.QUERY_SPLIT ) + queryParameters;
        }
        LOGGER.debug("Start to request the url " + requestUrl);
        String response;
        int responseCode;
        try {
            //构造请求连接
            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (this.proxy == null ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(this.proxy));
            //设置请求属性
            this.commonConnectionProperties.forEach(httpURLConnection::setRequestProperty);
            if (connectionProperties != null) {
                connectionProperties.forEach(httpURLConnection::setRequestProperty);
            }

            //设置请求方法
            if (requestMethod == null) {
                requestMethod = RequestMethod.GET;
                if (requestBody != null) {
                    //如果请求体不为空则设置为POST
                    requestMethod = RequestMethod.POST;
                }
            }
            httpURLConnection.setRequestMethod(requestMethod.name());
            if (requestMethod == RequestMethod.POST) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
            }

            //打开连接
            httpURLConnection.connect();

            //如果是POST请求则写数据
            if (requestMethod == RequestMethod.POST) {
                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.writeBytes(this.objectMapper.writeValueAsString(requestBody));
                outputStream.flush();
                outputStream.close();
            }

            //获取响应
            responseCode = httpURLConnection.getResponseCode();
            response = IOUtils.toString(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Fail to request the target url due to exception found", e);
            return null;
        }

        //处理结果
        if (StringUtils.isBlank(response)) {
            LOGGER.info("Got empty response from remote server");
            return null;
        }
        LOGGER.debug("The response content from remote server is ");
        LOGGER.debug(response);
        //检查响应码，响应码不为2XX则认为请求失败
        if (responseCode < HttpStatus.OK.value() || responseCode >= HttpStatus.MULTIPLE_CHOICES.value()) {
            throw new Exception("Remote server response error response with code " + responseCode);
        }
        //转换响应结果
        try {
            return this.objectMapper.readValue(response, responseType);
        } catch (Exception e) {
            LOGGER.error("Fail to convert the response result to target type due to exception found", e);
            return null;
        }
    }
}
