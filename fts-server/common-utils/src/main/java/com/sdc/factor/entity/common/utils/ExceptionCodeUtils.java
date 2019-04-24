package com.sdc.factor.entity.common.utils;

import com.sdc.factor.entity.common.api.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialExpiredException;
import java.util.HashMap;
import java.util.Map;

/**
 * Exception编码转化工具
 *
 * @author Sean
 * @since 2019-04-07
 */
public class ExceptionCodeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCodeUtils.class);

    private static final Map<Class<? extends Exception>, Integer> AUTHENTICATION_EXCEPTION_MAPPING = new HashMap<>();

    //填充缓存
    static {
        //账号已过期
        AUTHENTICATION_EXCEPTION_MAPPING.put(AccountExpiredException.class, 0);
        //账号锁定
        AUTHENTICATION_EXCEPTION_MAPPING.put(AccountStatusException.class, 100102);
        //密码在上下文环境中不存在
        AUTHENTICATION_EXCEPTION_MAPPING.put(AuthenticationCredentialsNotFoundException.class, 100100);
        //鉴权业务框架发生异常
        AUTHENTICATION_EXCEPTION_MAPPING.put(AuthenticationServiceException.class, 100100);
        //账号密码不匹配
        AUTHENTICATION_EXCEPTION_MAPPING.put(BadCredentialsException.class, 100103);
        //密码过期
        AUTHENTICATION_EXCEPTION_MAPPING.put(CredentialExpiredException.class, 100108);
        //账号已禁用
        AUTHENTICATION_EXCEPTION_MAPPING.put(DisabledException.class, 100102);
        //账号密码不匹配（或者密码不被信任）
        AUTHENTICATION_EXCEPTION_MAPPING.put(InsufficientAuthenticationException.class, 100103);
        //内部错误
        AUTHENTICATION_EXCEPTION_MAPPING.put(InternalAuthenticationServiceException.class, 100100);
        //账号锁定
        AUTHENTICATION_EXCEPTION_MAPPING.put(LockedException.class, 100102);
        //digest nonce过期
        AUTHENTICATION_EXCEPTION_MAPPING.put(NonceExpiredException.class, 100100);
        //内部错误，无可用的provider
        AUTHENTICATION_EXCEPTION_MAPPING.put(ProviderNotFoundException.class, 100100);
        //账号不存在
        AUTHENTICATION_EXCEPTION_MAPPING.put(UsernameNotFoundException.class, 100101);
    }

    /**
     * 将shiro exception转换为对应的错误消息编码
     * @param exception
     * @return
     */
    public static int convertAuthenticationExceptionToCode(Exception exception) {
        if (exception == null) {
            return RestResponse.BIZ_FAIL;
        }
        Integer code = AUTHENTICATION_EXCEPTION_MAPPING.get(exception.getClass());
        if (code == null) {
            if (exception instanceof AuthenticationException) {
                //Authentication通用错误
                code = 100199;
            } else {
                code = RestResponse.BIZ_FAIL;
            }
        }
        LOGGER.debug("Following shiro exception has been converted to error code " + code);
        LOGGER.debug(exception.getMessage(), exception);
        return code;
    }
}
