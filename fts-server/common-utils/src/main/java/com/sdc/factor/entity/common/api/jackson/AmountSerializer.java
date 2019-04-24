package com.sdc.factor.entity.common.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sdc.factor.entity.common.constants.WebConstants;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 金额类型序列化处理类
 *
 * @author nicholas
 * @since 2018-12-11 16:40
 */
public class AmountSerializer extends JsonSerializer<Number> {

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        BigDecimal decimalValue = new BigDecimal(String.valueOf(value));
        decimalValue = decimalValue.divide(WebConstants.AMOUNT_CONVERSION_RATIO);
        gen.writeNumber(decimalValue);
    }

}
