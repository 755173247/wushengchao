package com.sdc.factor.entity.common.entity.convert;

import javax.persistence.AttributeConverter;
import java.util.Date;

/**
 * 系统自定义日期出库入库处理器
 * @author nicholas
 * @since 2018-12-07
 */
public class DateTypeConverter implements AttributeConverter<Date, Long> {

    private static final long MILLISECOND_CONVERT = 1000;

    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        if (attribute == null) {
            return 0L;
        } else {
            return attribute.getTime() / MILLISECOND_CONVERT;
        }
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        if (dbData == null || dbData == 0L) {
            return null;
        } else {
            return new Date(dbData * MILLISECOND_CONVERT);
        }
    }
}
