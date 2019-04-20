package com.sdc.factor.common.beans.pagequery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 运营平台分页查询filterMap的参数必须使用此封装类型
 *
 * @author nicholas
 * @since 2018-12-12 20:00
 */
public class FilterField<T> implements Serializable {

    /**
     * 请求参数名
     */
    private String name;

    /**
     * 请求参数是否为空
     */
    private boolean isEmpty;

    /**
     * 数据类型
     * {@link com.sdc.factor.common.constants.PageRequestConstants.RequestDataType}
     */
    private String dataType;

    /**
     * 数值类型的比较方式(dataType=number的时候才有值)
     * {@link com.sdc.factor.common.constants.PageRequestConstants.ComparisonMode}
     */
    private String comparisonMode;

    /**
     * 请求的每个参数的具体值
     */
    private List<T> values;

    /**
     * 是否支持排序
     */
    private boolean supportSort;

    /**
     * 排序时的顺序号
     */
    private int orderSequence;

    /**
     * 排序方式
     * {@link com.sdc.factor.common.constants.PageRequestConstants.SupportedSortOrder}
     */
    private String sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public FilterField setEmpty(boolean empty) {
        isEmpty = empty;
        return this;
    }

    public String getComparisonMode() {
        return comparisonMode;
    }

    public FilterField setComparisonMode(String comparisonMode) {
        this.comparisonMode = comparisonMode;
        return this;
    }

    public List<T> getValues() {
        return values;
    }

    public FilterField setValues(List<T> values) {
        this.values = values;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public FilterField setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public boolean isSupportSort() {
        return supportSort;
    }

    public FilterField setSupportSort(boolean supportSort) {
        this.supportSort = supportSort;
        return this;
    }

    public int getOrderSequence() {
        return orderSequence;
    }

    public FilterField setOrderSequence(int orderSequence) {
        this.orderSequence = orderSequence;
        return this;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public FilterField setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public FilterField() {
    }

    public boolean valueIsNull() {
        if(getValues() == null || getValues().isEmpty()) {
            return true;
        }
        for (T t : values) {
            if(t != null) {
                return false;
            }
        }
        return true;
    }


    public void addValue(T value) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        values.add(value);
    }
}
