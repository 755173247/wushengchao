package com.sdc.factor.common.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 日期序列化
 *
 * @author sean
 * @since 2018-12-12 01:28
 */
public class TimestampSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNumber(0L);
        } else {
            gen.writeNumber(value.getTime());
        }
    }
}
