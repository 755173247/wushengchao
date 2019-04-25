package com.sdc.factor.common.web;

import com.sdc.factor.common.annotations.CurrentUser;
import com.sdc.factor.common.constants.WebConstants;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 *
 * @author sean
 * @since 2018-12-12 11:11
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute(WebConstants.REQUEST_SCOPE_CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
    }
}
