package com.sdc.factor.common.constants;

import java.math.BigDecimal;

/**
 * web端常用参数
 *
 * @author Sean
 * @since 2019-04-05
 */
public interface WebConstants {

    /**
     * query传参的分隔符
     */
    String QUERY_SPLIT = "?";
    /**
     * 参数分隔符
     */
    String PARAM_SPLIT = "&";

    /**
     * 参数与值的连接符
     */
    String PARAM_VALUE_JOIN = "=";

    /**
     * 布尔值对应int值
     */
    int BOOLEAN_TRUE = 1;
    int BOOLEAN_FALSE = 0;

    /**
     * 百分比数据与前端交互换算比例，请求的参数需要乘以此比例，返回的数据除以
     */
    BigDecimal PERCENTAGE_CONVERSION_RATIO = new BigDecimal(10000);

    /**
     * 金额数据与前端交互换算比例，请求的参数需要乘以此比例，返回的数据除以
     */
    BigDecimal AMOUNT_CONVERSION_RATIO = BigDecimal.ONE;

    /**
     * 请求头时间戳
     */
    String LPR_TIMESTAMP_HEADER = "LPXH-TIMESTAMP";

    /**
     * 请求头签名
     */
    String LPR_SIGNATURE_HEADER = "LPXH-SIGNATURE";

    /**
     * Query传参时间戳
     */
    String LPR_TIMESTAMP_PARAM = "_ts";

    /**
     * Query传参签名
     */
    String LPR_SIGNATURE_PARAM = "_signature";

    /**
     * Query传参语言
     */
    String LPR_LANGUAGE_PARAM = "language";

    /**
     * Request域内的当前用户参数名
     */
    String REQUEST_SCOPE_CURRENT_USER = "current_user";

    /**
     * Request域内的当前请求参数对象
     */
    String REQUEST_SCOPE_BODY_PARAM = "body_param";

    /**
     * cookie中的客户端标志位，任何接口响应前检查request中是否存在client_key是否存在值，如果不存在则返回一个client_key
     */
    String COOKIE_CLIENT_KEY = "lpxh_client_key";

    /**
     * 请求级别防止重复提交的key
     */
    String REQUEST_SCOPE_DUPLICATE_KEY = "duplicate_key";

    /**
     * Request域内的当前请求的token是否过期标志位参数
     */
    String REQUEST_SCOPE_TOKEN_EXPIRED = "is_token_expired";

    String APP_CHANNEL_USER_AGENT_PREFIX = "AppChannel";
}
