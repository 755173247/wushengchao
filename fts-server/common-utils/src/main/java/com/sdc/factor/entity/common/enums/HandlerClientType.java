package com.sdc.factor.entity.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 控制器的端类型
 *
 * @author Sean
 * @since 2019-04-05
 */
public enum HandlerClientType implements IEnum<String> {

    /**
     * app端控制器
     */
    APP("app"),
    /**
     * 运营管理平台控制器
     */
    MANAGEMENT("mgt");

    private String code;

    HandlerClientType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.getCode();
    }
}
