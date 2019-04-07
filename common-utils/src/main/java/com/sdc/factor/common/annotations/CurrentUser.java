package com.sdc.factor.common.annotations;

import java.lang.annotation.*;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 *
 * @author sean
 * @since 2018-12-12 11:00
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

}
