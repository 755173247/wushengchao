package com.sdc.factor.common.annotations;

import com.sdc.factor.common.utils.DATE;

import java.lang.annotation.*;

/**
 * Excel导出字段配置
 *
 * @author Harway
 * @version v1.0
 * @since 2018/12/27
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelExportField {

    // 指定导出的字段名称，用于标题显示
    String fieldName() default "";

    // 日期格式
    String dateFormat() default DATE.DATE_TIME_PATTERN;

    // 后缀（如%）
    String suffix() default "";

    // 顺序
    int order() default 0;

    // 布尔类型值导出指定描述，逗号前表示为true时的描述
    String boolValues() default "1,0";
}
