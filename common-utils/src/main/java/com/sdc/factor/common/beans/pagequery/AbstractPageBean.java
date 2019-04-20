package com.sdc.factor.common.beans.pagequery;

import com.sdc.factor.common.annotations.PageQueryField;
import com.sdc.factor.common.constants.PageRequestConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分页查询请求bean抽象类
 *
 * @author nicholas
 * @since 2018/12/14 11:52
 */
public abstract class AbstractPageBean implements Serializable {

    public static final String BEGIN_STRING = "Begin";
    public static final String END_STRING = "End";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPageBean.class);

    /**
     * 授权token
     */
    private String authorization;

    private PageParameter pageParameter;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public PageParameter convertToPageParameter() {
        List<SortSqlData> sortSqlDatas = new ArrayList<>();
        pageParameter = new PageParameter();
        Class clazz = this.getClass();
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            this.buildConditionForPageParameter(field, sortSqlDatas);
        }

        if (!sortSqlDatas.isEmpty()) {
            // 排序sortFields
            sortSqlDatas.sort((SortSqlData o1, SortSqlData o2) -> {
                if (o1.getFilterField().getOrderSequence() > o2.getFilterField().getOrderSequence()) {
                    return 1;
                } else {
                    return -1;
                }
            });

            this.buildSortSqlForPageParameter(sortSqlDatas);
        }

        return pageParameter;
    }


    private void buildConditionForPageParameter(Field field, List<SortSqlData> sortSqlDatas) {
        Object fieldValue = this.getFieldValue(field);
        if (fieldValue == null) {
            return;
        }

        PageQueryField pageQueryField = field.getAnnotation(PageQueryField.class);
        if (pageQueryField == null) {
            LOGGER.warn("No annotation named PageQueryField for field name: {}", field.getName());
            return;
        }

        if (fieldValue instanceof FilterField) {
            FilterField filterField = (FilterField) fieldValue;
            // 查询参数没有对应值，不构造SQL

            // 是否支持排序设置
            if (filterField.isSupportSort() && StringUtils.isNotBlank(filterField.getSortOrder())) {
                sortSqlDatas.add(new SortSqlData().setFilterField(filterField).setPageQueryField(pageQueryField));
            }

            if (filterField.getValues() == null || filterField.isEmpty() || filterField.getValues().isEmpty()) {
                return;
            }

            String dataType = filterField.getDataType();
            if (PageRequestConstants.RequestDataType.NUMBER.equalsIgnoreCase(dataType)) {
                buildNumberTypeSql(filterField, pageQueryField, field.getName());

            } else if (PageRequestConstants.RequestDataType.DATE.equalsIgnoreCase(dataType)
                    || (PageRequestConstants.RequestDataType.DATE_TIME.equalsIgnoreCase(dataType))) {
                buildDateTypeSql(filterField, pageQueryField, field.getName());

            } else if (PageRequestConstants.RequestDataType.SET.equalsIgnoreCase(dataType)) {
                buildSetTypeSql(filterField, pageQueryField, field.getName());

            } else if (PageRequestConstants.RequestDataType.STRING.equalsIgnoreCase(dataType)) {
                buildStringTypeSql(filterField, pageQueryField, field.getName());

            } else if (PageRequestConstants.RequestDataType.BOOLEAN.equalsIgnoreCase(dataType)) {
                buildBooleanTypeSql(filterField, pageQueryField, field.getName());

            } else {
                throw new IllegalArgumentException("Unsupported request data type:[ " + dataType + "]");
            }

        } else {
            // filterMap外部参数几乎不会出现，暂不支持filterMap外的参数类型为集合的
            // String typeHandler = this.getTypeHandlerClassName(fieldValue, pageQueryField.privacy());
            SqlGenerator.generateSql(SqlGenerator.EQUAL, pageQueryField.tableAlias(), pageQueryField.column(),
                    new String[]{field.getName()}, null, null);
        }
    }

    private Object getFieldValue(Field field) {
        Object fieldValue = null;
        try {

            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(this.getClass(), field.getName());
            fieldValue = pd.getReadMethod().invoke(this);
        } catch (Exception e) {
            LOGGER.error("Get field value failed for field:{}, maybe not exists getter method", e);
        }

        /*if(fieldValue == null) {
            LOGGER.info("Field value is null for field name : {}", field.getName());
        }*/
        return fieldValue;
    }

    private void buildNumberTypeSql(FilterField filterField, PageQueryField pageQueryField, String fieldName) {
        // 若为数字比较区间，可能有两个比较符
        String symbolOne = null;
        String symbolTwo = null;
        if (PageRequestConstants.ComparisonMode.EQUAL.equalsIgnoreCase(filterField.getComparisonMode())) {
            symbolOne = SqlGenerator.EQUAL;
        } else if (PageRequestConstants.ComparisonMode.GREATER_THAN.equalsIgnoreCase(filterField.getComparisonMode())) {
            symbolOne = SqlGenerator.GREATER_THAN;
        } else if (PageRequestConstants.ComparisonMode.LESS_THAN.equalsIgnoreCase(filterField.getComparisonMode())) {
            symbolOne = SqlGenerator.LESS_THAN;
        } else if (PageRequestConstants.ComparisonMode.RANGE.equalsIgnoreCase(filterField.getComparisonMode())) {
            symbolOne = filterField.getValues().get(0) != null ? SqlGenerator.GREATER_THAN_OR_EQUAL : null;
            symbolTwo = (filterField.getValues().size() > 1 && filterField.getValues().get(0) != null) ?
                    SqlGenerator.LESS_THAN_OR_EQUAL : null;
        } else {
            throw new IllegalArgumentException("Unsupported symbol '" + filterField.getComparisonMode() +
                    "' for parameter '" + filterField.getName() + "'");
        }
        if (StringUtils.isNotBlank(symbolOne)) {
            buildNumberTypeSqlForOneSymbol(symbolOne, fieldName + BEGIN_STRING, filterField.getValues().get(0), pageQueryField);
        }
        if (StringUtils.isNotBlank(symbolTwo)) {
            buildNumberTypeSqlForOneSymbol(symbolTwo, fieldName + END_STRING, filterField.getValues().get(1), pageQueryField);
        }

    }

    // 一个参数的单个比较值SQL生成
    private void buildNumberTypeSqlForOneSymbol(String symbol, String paramKey, Object value, PageQueryField pageQueryField) {
        String paramName = this.getParamName(paramKey);
        String sql = SqlGenerator.generateSql(symbol, pageQueryField.tableAlias(), pageQueryField.column(),
                new String[]{paramName}, null, null);
        this.pageParameter.getConditions().add(sql);
        this.pageParameter.getParameter().put(paramKey, value);
    }

    /**
     * 统一的传参命名获取方式
     *
     * @param fieldName 参数原始名称
     * @return 新的参数名称
     */
    private String getParamName(String fieldName) {
        StringBuilder paramName = new StringBuilder(PageParameter.DAO_PAGE_PARAMETER_NAME).append(SqlGenerator.POINT)
                .append(PageParameter.MAP_PARAMS_NAME).append(SqlGenerator.POINT).append(fieldName);
        return paramName.toString();
    }

    /**
     * 日期区间查询条件构造
     *
     * @param filterField    自定义类型
     * @param pageQueryField 所用注解
     */
    private void buildDateTypeSql(FilterField filterField, PageQueryField pageQueryField, String fieldName) {
        // String typeHandler = SqlGenerator.DATE_TYPE_HANDLER_CLASS;
        String paramKeyBegin = fieldName + BEGIN_STRING;
        String paramNameBegin = this.getParamName(paramKeyBegin);
        String sqlOne = SqlGenerator.generateSql(SqlGenerator.GREATER_THAN_OR_EQUAL, pageQueryField.tableAlias(), pageQueryField.column(),
                new String[]{paramNameBegin}, null, null);

        this.pageParameter.getConditions().add(sqlOne);
        this.pageParameter.getParameter().put(paramKeyBegin, filterField.getValues().get(0));

        if (filterField.getValues().size() > 1 && filterField.getValues().get(1) != null) {
            String paramKeyEnd = filterField.getName() + END_STRING;
            String paramNameEnd = this.getParamName(paramKeyEnd);
            String sqlTwo = SqlGenerator.generateSql(SqlGenerator.LESS_THAN_OR_EQUAL, pageQueryField.tableAlias(), pageQueryField.column(),
                    new String[]{paramNameEnd}, null, null);
            this.pageParameter.getConditions().add(sqlTwo);
            this.pageParameter.getParameter().put(paramKeyEnd, filterField.getValues().get(1));
        }

    }

    private void buildSetTypeSql(FilterField filterField, PageQueryField pageQueryField, String fieldName) {
        String[] paramNames = new String[filterField.getValues().size()];
        for (int i = 0; i < filterField.getValues().size(); i++) {
            String paramKeyInMap = fieldName + i;
            paramNames[i] = this.getParamName(paramKeyInMap);
            this.pageParameter.getParameter().put(paramKeyInMap, filterField.getValues().get(i));
        }
        String sql = SqlGenerator.generateSql(SqlGenerator.IN, pageQueryField.tableAlias(), pageQueryField.column(),
                paramNames, null, null);

        this.pageParameter.getConditions().add(sql);
    }

    private void buildStringTypeSql(FilterField filterField, PageQueryField pageQueryField, String fieldName) {
        String paramName = this.getParamName(fieldName);
        String symbol;
        // 是否支持模糊查询，如果支持模糊查询，只能有一个值进行模糊查询
        if (pageQueryField.supportFuzziness()) {
            symbol = SqlGenerator.LIKE;
            this.pageParameter.getParameter().put(fieldName,
                    "%" + filterField.getValues().get(0) + "%");
        } else {
            symbol = SqlGenerator.EQUAL;
            this.pageParameter.getParameter().put(fieldName, filterField.getValues().get(0));
        }
        String sql = SqlGenerator.generateSql(symbol, pageQueryField.tableAlias(), pageQueryField.column(),
                new String[]{paramName}, null, null);
        this.pageParameter.getConditions().add(sql);
    }

    private void buildBooleanTypeSql(FilterField filterField, PageQueryField pageQueryField, String fieldName) {
        String subSql;
        Boolean boolValue = (Boolean) filterField.getValues().get(0);
        if (boolValue != null && boolValue) {
            subSql = pageQueryField.trueValueSubSql();
        } else {
            subSql = pageQueryField.falseValueSubSql();
        }
        String sql = SqlGenerator.generateSql(null, pageQueryField.tableAlias(), pageQueryField.column(),
                new String[0], null, subSql);
        this.pageParameter.getConditions().add(sql);
    }

    /**
     * 创建排序SQL
     * @param sortSqlDataList
     */
    private void buildSortSqlForPageParameter(List<SortSqlData> sortSqlDataList) {
        StringBuilder sortSql = new StringBuilder(SqlGenerator.ORDER);

        for (SortSqlData sortSqlData : sortSqlDataList) {
            if (StringUtils.isNotBlank(sortSqlData.getPageQueryField().tableAlias())) {
                sortSql.append(sortSqlData.getPageQueryField().tableAlias()).append(SqlGenerator.POINT);
            }
            sortSql.append(sortSqlData.getPageQueryField().column()).append(" ");
            sortSql.append(sortSqlData.getFilterField().getSortOrder()).append(SqlGenerator.COMMA);
        }
        this.pageParameter.setOrderSql(sortSql.toString().substring(0, sortSql.length() - 1));
    }

    private String getTypeHandlerClassName(Object value, boolean privacy) {
        if (privacy) {
            return SqlGenerator.PRIVACY_FIELD_TYPE_HANDLER_CLASS;
        } else if (value instanceof Date) {
            return SqlGenerator.DATE_TYPE_HANDLER_CLASS;
        } else {
            return null;
        }
    }

    private static class SortSqlData {
        private FilterField filterField;
        private PageQueryField pageQueryField;

        public FilterField getFilterField() {
            return filterField;
        }

        public SortSqlData setFilterField(FilterField filterField) {
            this.filterField = filterField;
            return this;
        }

        public PageQueryField getPageQueryField() {
            return pageQueryField;
        }

        public SortSqlData setPageQueryField(PageQueryField pageQueryField) {
            this.pageQueryField = pageQueryField;
            return this;
        }
    }

    private static class SqlGenerator {

        private static final String DATE_TYPE_HANDLER_CLASS = "com.lepin.instalments.typehandler.DateTypeHandler";
        private static final String PRIVACY_FIELD_TYPE_HANDLER_CLASS = "com.lepin.instalments.typehandler.PrivacyFieldTypeHandler";


        private static final String AND = " and ";
        private static final String EQUAL = " = ";
        private static final String GREATER_THAN = " > ";
        private static final String LESS_THAN = " < ";
        private static final String LESS_THAN_OR_EQUAL = " <= ";
        private static final String GREATER_THAN_OR_EQUAL = " >= ";
        private static final String IN = " in ";
        private static final String LIKE = " like ";
        private static final String ORDER = " order by ";

        private static final String PREFIX = " #{";
        private static final String SUFFIX = "} ";
        private static final String COMMA = ",";
        private static final String POINT = ".";
        private static final String TYPE_HANDLER_IS = "typeHandler=";

        /**
         * @param symbol      符号
         * @param tableAlias  表的别名
         * @param column      列名
         * @param paramNames  查询参数名
         * @param typeHandler 类型处理器
         * @return Sql
         */
        protected static String generateSql(String symbol, String tableAlias, String column, String[] paramNames, String typeHandler, String subSql) {
            StringBuilder sb = new StringBuilder(SqlGenerator.AND);
            if (StringUtils.isNotBlank(tableAlias)) {
                sb.append(tableAlias).append(SqlGenerator.POINT);
            }
            sb.append(column).append(symbol == null ? "" : symbol);
            if (IN.equalsIgnoreCase(symbol)) {
                sb.append(" ( ");
            }
            for (int i = 0; i < paramNames.length; i++) {
                sb.append(SqlGenerator.PREFIX).append(paramNames[i]);
                if (StringUtils.isNotBlank(typeHandler)) {
                    sb.append(SqlGenerator.COMMA).append(SqlGenerator.TYPE_HANDLER_IS).append(typeHandler);
                }
                sb.append(SqlGenerator.SUFFIX);
                if (i < paramNames.length - 1) {
                    sb.append(SqlGenerator.COMMA);
                }
            }
            if (IN.equalsIgnoreCase(symbol)) {
                sb.append(" ) ");
            }
            if (subSql != null) {
                sb.append(subSql);
            }
            return sb.toString();
        }
    }
}

