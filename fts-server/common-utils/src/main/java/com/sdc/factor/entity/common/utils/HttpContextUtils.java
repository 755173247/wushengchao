package com.sdc.factor.entity.common.utils;

import com.sdc.factor.entity.common.constants.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Http请求上下文相关帮助方法
 *
 * @author Sean
 * @since 2019-04-20
 */
public class HttpContextUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpContextUtils.class);

	/**
	 * 获得当前的请求
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
	}

	/**
	 * 从user agent中获取渠道参数
	 * @return
	 */
	public static String getChannelFromUserAgent() {
		HttpServletRequest request = getHttpServletRequest();
		String channel = null;
		if (request != null) {
			String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
			if (StringUtils.isNotBlank(userAgent)) {
				//以空格分隔
				String[] parts = userAgent.split(" ");
				String channelPart = Stream.of(parts).filter(s -> s.startsWith(WebConstants.APP_CHANNEL_USER_AGENT_PREFIX)).findAny().orElse("");
				if (StringUtils.isNotBlank(channelPart)) {
					channel = channelPart.replace(WebConstants.APP_CHANNEL_USER_AGENT_PREFIX, "");
					if (StringUtils.isNotBlank(channel) && channel.length() > 1) {
						channel = channel.substring(1);
					}
				}
			}
		}
		return channel == null ? "" : channel.trim();
	}

	/**
	 * 获得指定的消息
	 * @param code 消息编码
	 * @param params 消息参数
	 * @return 消息内容
	 */
	public static String getMessage(int code, Object... params) {
		return getMessage(String.valueOf(code), params);
	}

	/**
	 * 获得指定的消息
	 * @param code 消息编码
	 * @param params 消息参数
	 * @return 消息内容
	 */
	public static String getMessage(String code, Object... params) {
		HttpServletRequest request = getHttpServletRequest();
		String content = "";
		if (null != request) {
			//优先从请求参数中获取语言参数
			Locale locale = getLocaleFromRequest(request);
			ApplicationContext applicationContext = RequestContextUtils.findWebApplicationContext(request);
			if (applicationContext != null) {
				try {
					content = applicationContext.getMessage(code, params, locale);
				} catch (Exception e) {
					LOGGER.error("Fail to get the message content for code " + code + " with locale " + locale.getDisplayName(), e);
				}
			}
		} else {
			content = code;
		}
		return content;
	}

	/**
	 * 从请求中获取语言信息
	 * @param request
	 * @return
	 */
	public static Locale getLocaleFromRequest(HttpServletRequest request) {
		if (request == null) {
			return null;
		} else {
			Locale locale = (Locale) request.getAttribute(WebConstants.SDC_LANGUAGE_PARAM);
			if (locale == null) {
				String language = request.getParameter(WebConstants.SDC_LANGUAGE_PARAM);
				if (StringUtils.isNotBlank(language)) {
					String country = "";
					int dash = language.indexOf('-');
					if (dash > -1) {
						country = language.substring(dash + 1).trim();
						language = language.substring(0,dash).trim();
						if (StringUtils.isNoneBlank(language, country)) {
							locale = new Locale(language, country);
						}
					}
				}
				if (locale == null) {
					locale = RequestContextUtils.getLocale(request);
				}
				request.setAttribute(WebConstants.SDC_LANGUAGE_PARAM, locale);
				return locale;
			} else {
				return locale;
			}
		}
	}
}
