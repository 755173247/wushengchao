package com.sdc.factor.common.constants;
/**
 * 运营平台分页查询参数常量定义
 *
 * @author nicholas
 * @since 2018-12-12 20:00
 */
public interface PageRequestConstants {

    /**
     * 数字比较方式
     */
    interface ComparisonMode {

        String EQUAL = "eq";

        String LESS_THAN = "lt";

        String GREATER_THAN = "gt";

        /**
         * 数字类型区间比较
         */
        String RANGE = "range";
    }

    /**
     * 分页查询固定请求参数名
     */
    interface PageRequestParamNames {

        String PAGE =  "page";

        String PAGE_SIZE = "pageSize";

        String SORT_BY = "sortBy";

        String SORT_ORDER = "sortOrder";

        String FILTER_MAP = "filterMap";

    }

    /**
     * 支持的排序
     */
    interface SupportedSortOrder {
        String ASC = "asc";
        String DESC = "desc";
    }

    /**
     * 请求参数数据类型
     */
    public interface RequestDataType {

        /**
         * 用户自主输入的字符内容等
         */
        String STRING = "string";

        /**
         * 下拉多选项，多值集合
         */
        String SET = "set";

        /**
         * 做比较的数字
         */
        String NUMBER = "number";

        /**
         * 日期类型，目前只支持区间
         */
        String DATE = "date";

        /**
         * 时间类型，目前只支持区间
         */
        String DATE_TIME = "datetime";

        /**
         * 布尔类型
         */
        String BOOLEAN = "boolean";

    }

}
