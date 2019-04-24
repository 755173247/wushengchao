package com.sdc.factor.entity.common.entity.convert;

import com.sdc.factor.entity.common.enums.IEnum;
import com.sdc.factor.entity.common.utils.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

/**
 * 枚举类型转换器
 *
 * @param <T> 实际的枚举类型
 *
 * @author Sean
 * @since 2019-04-05
 */
public abstract class AbstractEnumTypeConverter<T extends Enum<?> & IEnum> implements AttributeConverter<T, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEnumTypeConverter.class);

    /**
     * 目标值的类型
     */
    private Class<T> type;

    protected AbstractEnumTypeConverter(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        } else {
            return String.valueOf(attribute.getValue());
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        } else {
            return EnumUtils.valueOf(type, dbData);
        }
    }
}
