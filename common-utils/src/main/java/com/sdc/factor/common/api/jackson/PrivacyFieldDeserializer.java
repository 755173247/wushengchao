package com.sdc.factor.common.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sdc.factor.common.beans.PrivacyField;

import java.io.IOException;

/**
 * Privacy Field反序列化
 *
 * @author Sean
 * @since 2018-12-12 16:25
 */
public class PrivacyFieldDeserializer extends JsonDeserializer<PrivacyField> {

    @Override
    public PrivacyField deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return PrivacyField.of(jsonParser.getValueAsString());
    }
}
