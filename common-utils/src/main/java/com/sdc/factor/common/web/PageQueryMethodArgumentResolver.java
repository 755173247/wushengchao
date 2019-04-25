package com.sdc.factor.common.web;

import com.sdc.factor.common.annotations.PageBean;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，当含有@PageQueryDTO注解的方法, 把请求参数转换成对应的RequestDTO
 *
 * @author nicholas
 * @since 2018-12-13 14:30
 */
public class PageQueryMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private PageQueryParser pageQueryParser = new PageQueryParser();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageBean.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return pageQueryParser.analyzeRequestParams(webRequest.getParameterMap(), parameter.getParameterType());
    }
}
