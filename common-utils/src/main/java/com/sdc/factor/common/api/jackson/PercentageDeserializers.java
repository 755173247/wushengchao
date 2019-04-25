package com.sdc.factor.common.api.jackson;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sdc.factor.common.constants.WebConstants;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 百分比参数反序列化处理类
 *
 * @author nicholas
 * @since 2018-12-11 16:40
 */
public class PercentageDeserializers {

    public static class DoublePercentageDeserializer extends JsonDeserializer<Double> {
        @Override
        public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.PERCENTAGE_CONVERSION_RATIO);
            return decimalValue.doubleValue();
        }
    }

    public static class IntegerPercentageDeserializer extends JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.PERCENTAGE_CONVERSION_RATIO);
            return decimalValue.intValue();
        }
    }

    public static class LongPercentageDeserializer extends JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            BigDecimal decimalValue = new BigDecimal(p.getValueAsString()).multiply(WebConstants.PERCENTAGE_CONVERSION_RATIO);
            return decimalValue.longValue();
        }
    }

    public static class BigDecimalPercentageDeserializer extends JsonDeserializer<BigDecimal> {

        @Override
        public BigDecimal deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return new BigDecimal(p.getValueAsString()).multiply(WebConstants.PERCENTAGE_CONVERSION_RATIO);
        }
    }

}
