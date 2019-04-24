package com.sdc.factor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdc.factor.entity.common.utils.AesEncryptUtils;
import com.sdc.factor.entity.common.web.AbstractHttpClient;
import com.sdc.factor.third.entity.business.SynchronousReceiptRequest;
import com.sdc.factor.third.entity.business.SysResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wushengchao
 * @create 2019-04-23
 */
@Service
public class InvoiceServiceImpl extends AbstractHttpClient implements InvoiceService {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public SysResult vatTransferThirdPartyApi(SynchronousReceiptRequest synchronousReceiptRequest) throws  Exception{
        String requestUrl="https://{baseUrl}/m3/bill/invoice/sys/check?access_token={access_token}";
        /** 待加密请求参数  */
        String requestParameter = objectMapper.writeValueAsString(synchronousReceiptRequest);
        /** 加密密钥  */
        String encryptKey = "1234567887654321";
        String requestParametersJSON=AesEncryptUtils.encrypt(requestParameter,encryptKey);
        Map<String, String> requestParameters=new HashMap<>();
        requestParameters.put("requestParametersJSON",requestParametersJSON);
        Object requestBody="请求获取发票相关信息";
        Map<String, String> connectionProperties=new HashMap<>();
        connectionProperties.put("Content-Type","application/json;charset=utf-8");
        SysResult sysResult = new InvoiceServiceImpl().request(RequestMethod.POST, SysResult.class, requestUrl, requestParameters, requestBody, connectionProperties);
        Object data = sysResult.getData();
        /** 将json结果集转化为SysResult对象要先判断一下SysResult中的object类型  */
        String jsonData = objectMapper.writeValueAsString(sysResult);
        sysResult = SysResult.formatToPojo(jsonData, data.getClass());
        return sysResult;

    }


}
