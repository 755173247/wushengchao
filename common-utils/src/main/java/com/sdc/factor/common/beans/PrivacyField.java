package com.sdc.factor.common.beans;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * 用户隐私字段存储，入库加密，出库解密
 * 加密字段: 姓名、身份证、手机号、联系人的姓名手机号、email, 银行卡，工作单位、工作详细地址等等
 *
 * @author Sean
 * @since 2019-04-05
 */
public class PrivacyField implements Serializable {

    /**
     * 原始值
     */
    private String value;

    public Optional<String> getValue() {
        return Optional.ofNullable(this.value);
    }

    public PrivacyField setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 隐私字段构造器
     * @param value 原始值
     * @return
     */
    public static PrivacyField of(String value) {
        return new PrivacyField().setValue(value);
    }

    /**
     * 值是否为空或者为空字符串
     */
    public boolean isValueBlank() {
        return StringUtils.isBlank(this.value);
    }

    /**
     * 值是否为非空或者为非空字符串
     */
    public boolean isValueNotBlank() {
        return StringUtils.isNotBlank(this.value);
    }

    /**
     * 值是否为空
     */
    public boolean isValueEmpty() {
        return this.value == null;
    }
}
