package com.sdc.factor.entity.common.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sdc.factor.entity.common.constants.WebConstants;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 金额类型反序列化处理类
 *
 * @author nicholas
 * @since 2018-12-11 16:40
 */
public class AmountDeserializers {

    public static class DoubleAmountDeserializer extends JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.AMOUNT_CONVERSION_RATIO);
            return decimalValue.doubleValue();
        }
    }

    public static class IntegerAmountDeserializer extends JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.AMOUNT_CONVERSION_RATIO);
            return decimalValue.intValue();
        }
    }

    public static class LongAmountDeserializer extends JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.AMOUNT_CONVERSION_RATIO);
            return decimalValue.longValue();
        }
    }

}
