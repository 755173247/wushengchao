package com.sdc.factor.common.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sdc.factor.common.constants.WebConstants;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 百分比参数序列化处理类
 *
 * @author nicholas
 * @since 2018-12-11 16:40
 */
public class PercentageSerializer extends JsonSerializer<Number> {

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        BigDecimal decimalValue = new BigDecimal(String.valueOf(value));
        decimalValue = decimalValue.divide(WebConstants.PERCENTAGE_CONVERSION_RATIO);
        gen.writeNumber(decimalValue);
    }
}
