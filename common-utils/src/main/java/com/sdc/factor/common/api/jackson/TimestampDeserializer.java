package com.sdc.factor.common.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * 日期反序列化，前端传输时间戳（毫秒）
 *
 * @author sean
 * @since 2018-12-12 01:30
 */
public class TimestampDeserializer extends JsonDeserializer<Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampDeserializer.class);

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            if (StringUtils.isBlank(p.getValueAsString())) {
                return null;
            } else {
                Long timestamp = Long.valueOf(p.getValueAsString());
                return new Date(timestamp);
            }
        } catch (Exception e) {
            LOGGER.error("The timestamp value is " + p.getValueAsString());
            LOGGER.error("Fail to convert the timestamp value, it must be not a valid LONG value", e);
            return null;
        }
    }
}
