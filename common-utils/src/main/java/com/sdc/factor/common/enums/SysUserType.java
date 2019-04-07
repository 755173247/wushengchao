package com.sdc.factor.common.enums;

/**
 * 系统的用户类型
 *
 * @author sean
 * @since 2018-12-12 17:28
 */
public enum SysUserType implements IEnum<String> {

    CLIENT("client"), //客户，主要指使用app的用户
    OPERATOR("operator"), //运营者，指使用运营管理平台的用户
    SYSTEM("system"); //系统，第三方系统调用者

    private String code;

    SysUserType(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
