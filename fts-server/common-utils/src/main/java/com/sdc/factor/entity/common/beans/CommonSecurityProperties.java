package com.sdc.factor.entity.common.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 公共安全配置
 *
 * @author Sean
 * @since 2019-04-05
 */
@Getter
@Setter
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "sdcfactor.common.security")
public class CommonSecurityProperties {

    /**
     * 隐私字段默认加密盐
     */
    public static final String DEFAULT_PRIVACY_FIELD_KEY = PrivacyField.class.getSimpleName();

    /**
     * 默认密码加密盐
     */
    public static final String DEFAULT_PASSWORD_SALT = CommonSecurityProperties.class.getSimpleName();

    /**
     * 默认密码加密强度
     */
    public static final int DEFAULT_PASSWORD_CRYPT_STRENGTH = 3;

    /**
     * 隐私字段加密key
     */
    private String privacyFieldKey;

    /**
     * 密码加密盐
     */
    private String passwordSalt;

    /**
     * 密码加密强度
     */
    private int passwordCryptStrength;

    /**
     * jwt token的key
     */
    private String jwtTokenKey;

    /**
     * jwt token的生命周期，单位为小时
     */
    private int jwtTokenLifetime;
}
