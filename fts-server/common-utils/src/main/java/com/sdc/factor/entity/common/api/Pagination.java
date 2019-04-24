package com.sdc.factor.entity.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页响应数据
 *
 * @author sean
 * @since 2018-12-10 17:06
 */
@ApiModel("分页数据")
public class Pagination implements Serializable {

    @ApiModelProperty(value = "总记录数", required = true, dataType = "int")
    private int total;

    @ApiModelProperty(value = "分页大小", required = true, dataType = "int")
    private int pageSize;

    @ApiModelProperty(value = "当前页码", required = true, dataType = "int")
    private int currentPage;

    public int getTotal() {
        return total;
    }

    public Pagination setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Pagination setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Pagination setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }
}
