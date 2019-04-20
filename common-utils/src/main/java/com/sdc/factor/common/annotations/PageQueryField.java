package com.sdc.factor.common.annotations;

import java.lang.annotation.*;

/**
 * 分页查询PageBean字段通用注解
 *
 * @author nicholas
 * @since 2018/12/12 21:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface PageQueryField {

    /**
     * 该查询字段所对应的数据库表 在分页查询SQL中的别名，
     * 请在分页查询中，这里与SQL中，统一使用表的全名作为别名
     */
    String tableAlias() default "";

    // 该查询字段所对应的数据库列名
    String column();

    // 是否支持模糊查询
    boolean supportFuzziness() default false;

    String trueValueSubSql() default " is not null ";

    String falseValueSubSql() default " is null ";

    @Deprecated
    boolean privacy() default false;

}
