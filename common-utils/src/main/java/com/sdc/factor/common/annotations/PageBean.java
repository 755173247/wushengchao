package com.sdc.factor.common.annotations;


import java.lang.annotation.*;

/**
 * 运营平台分页查询Controller参数注解
 *
 * @author nicholas
 * @since 2018/12/12 21:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PARAMETER)
public @interface PageBean {

}
