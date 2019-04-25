package com.sdc.factor.common.annotations;

import java.lang.annotation.*;

/**
 * Excel导出表配置
 *
 * @author Harway
 * @version v1.0
 * @since 2018/12/27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelExportSheet {

    // 表名称
    String name() default "";
}
