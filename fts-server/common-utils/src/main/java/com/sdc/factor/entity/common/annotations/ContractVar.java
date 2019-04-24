package com.sdc.factor.entity.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 合同生产字段变量，需要在Model中标注出来
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface ContractVar {

    /**
     * 字段名称
     */
    String name() default "";

}
