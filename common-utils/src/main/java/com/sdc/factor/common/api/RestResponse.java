package com.sdc.factor.common.api;

import com.sdc.factor.common.error.BizException;
import com.sdc.factor.common.utils.HttpContextUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 接口返回数据的结构定义
 * 业务数据对象必须是标准的java bean，包含所有可序列化字段的getter和setter方法
 *
 * @param <T> 业务数据的数据类型
 */
@ApiModel(value = "接口响应")
public class RestResponse<T> implements Serializable {

    public static final int BIZ_OK = 0;

    public static final int BIZ_FAIL = 999999;

    /**
     * 响应编码
     */
    @ApiModelProperty(value = "业务处理结果编码", required = true, dataType = "int")
    private int code = BIZ_OK;

    /**
     * 响应描述
     */
    @ApiModelProperty(value = "业务处理结果描述", required = true, dataType = "string")
    private String msg = "ok";

    /**
     * 业务数据
     */
    @ApiModelProperty(value = "业务数据", required = false, dataType = "object")
    private T data = null;

    @ApiModelProperty(value = "分页信息", required = false, dataType = "object")
    private Pagination pagination = null;

    public RestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public RestResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public RestResponse<T> setMsg(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        }
        return this;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public RestResponse setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static RestResponse ok() {
        return new RestResponse();
    }

    public static <D> RestResponse<D> ok(D data) {
        return new RestResponse<D>().setData(data).setMsg(HttpContextUtils.getMessage(BIZ_OK));
    }

    public static RestResponse<?> error(BizException bizException) {
        String message = HttpContextUtils.getMessage(bizException.getCode(), bizException.getMessageParams().toArray());
        return new RestResponse<>().setCode(bizException.getCode()).setMsg(StringUtils.isNotBlank(message) ? message : bizException.getMessage());
    }

    public static RestResponse<?> error(String message) {
        return new RestResponse<>().setCode(BIZ_FAIL).setMsg(StringUtils.isNotBlank(message) ? message : HttpContextUtils.getMessage(BIZ_FAIL));
    }

    public static RestResponse<?> error(int code) {
        return new RestResponse<>().setCode(code).setMsg(HttpContextUtils.getMessage(code));
    }

    public static RestResponse<?> error(int code, List<Object> params) {
        String message = HttpContextUtils.getMessage(code, params == null ? Collections.emptyList().toArray() : params.toArray());
        return new RestResponse<>().setCode(code).setMsg(message);
    }

    public static RestResponse error(int code, String message) {
        return new RestResponse().setCode(code).setMsg(StringUtils.isNotBlank(message) ? message : HttpContextUtils.getMessage(code));
    }
}
