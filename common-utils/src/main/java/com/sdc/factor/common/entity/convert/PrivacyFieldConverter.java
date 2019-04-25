package com.sdc.factor.common.entity.convert;

import com.sdc.factor.common.beans.CommonSecurityProperties;
import com.sdc.factor.common.beans.PrivacyField;
import com.sdc.factor.common.utils.AesEncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

/**
 * 隐私字段类型转换器
 *
 * @author Sean
 * @since 2019-04-05
 */
public class PrivacyFieldConverter implements AttributeConverter<PrivacyField, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivacyFieldConverter.class);

    /**
     * 加密盐，系统启动时从配置中读取并设置
     */
    public static String ENCRYPT_KEY = CommonSecurityProperties.DEFAULT_PRIVACY_FIELD_KEY;

    @Override
    public String convertToDatabaseColumn(PrivacyField attribute) {
        if (attribute == null || !attribute.getValue().isPresent() || StringUtils.isBlank(attribute.getValue().orElse(""))) {
            return null;
        } else {
            return AesEncryptUtils.encrypt(attribute.getValue().get(), ENCRYPT_KEY);
        }
    }

    @Override
    public PrivacyField convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return PrivacyField.of(null);
        } else {
            return PrivacyField.of(AesEncryptUtils.decrypt(dbData.trim(), ENCRYPT_KEY));
        }
    }
}
