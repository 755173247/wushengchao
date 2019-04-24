package com.sdc.factor.entity.aop.aspects;

import com.sdc.factor.entity.common.annotations.RequiresAuthentication;
import com.sdc.factor.entity.common.constants.WebConstants;
import com.sdc.factor.entity.common.error.BizException;
import com.sdc.factor.entity.common.utils.HttpContextUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 在执行controller方法之前检查当前控制器方法是否需要登录，如果需要的话则通知用户登录，不需要则继续执行
 *
 * @author Sean
 * @since 2019-04-07
 */
@Component
@Aspect
public class RequiresAuthenticationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequiresAuthenticationAspect.class);

    @Pointcut("execution(public * com.sdc.factor.controller..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(final JoinPoint joinPoint) {
        //获得当前指定的方法
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        //先从类上寻找
        RequiresAuthentication annon = AnnotationUtils.findAnnotation(targetClazz, RequiresAuthentication.class);
        if (annon == null) {
            //再从方法上寻找
            MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            Class[] classes = ((MethodSignature) methodInvocationProceedingJoinPoint.getSignature()).getParameterTypes();
            Method method = ReflectionUtils.findMethod(targetClazz, methodName, classes);
            if (method != null) {
                annon = AnnotationUtils.findAnnotation(method, RequiresAuthentication.class);
            }
        }
        //当前方法需要登录
        if (annon != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //当前没有鉴权
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()) {
                HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
                Boolean isTokenExpired = false;
                if (request != null) {
                    isTokenExpired = (Boolean) request.getAttribute(WebConstants.REQUEST_SCOPE_TOKEN_EXPIRED);
                }
                if (isTokenExpired != null && isTokenExpired) {
                    throw new BizException(100105);
                } else {
                    throw new BizException(100104);
                }
            }
        }
    }
}
