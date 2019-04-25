package com.sdc.factor.common.api.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sdc.factor.common.beans.PrivacyField;

import java.io.IOException;

/**
 * Privacy Field的序列化
 *
 * @author sean
 * @since 2018-12-12 16:25
 */
public class PrivacyFieldSerializer extends JsonSerializer<PrivacyField> {

    @Override
    public void serialize(PrivacyField privacyField, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(privacyField == null ? null : privacyField.getValue().orElse(""));
    }
}
