package com.sdc.factor.entity.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Json工具类
 *
 * @author Sean
 * @since 2019-03-24
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static JavaType getCollectionGenericType(Class<? extends Collection> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将json数组字符串转换为数组
     * @param json json数组字符串
     * @param clazz 数组元素的数据类型
     * @param <T> 类型泛型
     */
    @SuppressWarnings({"unchecked"})
    public static <T> List<T> parseJsonArray(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyList();
        } else {
            List<T> items = null;
            try {
                items = (List<T>) OBJECT_MAPPER.readValue(json.trim(), getCollectionGenericType(ArrayList.class, clazz));
            } catch (Exception e) {
                LOGGER.error("Fail to convert " + json + " to list with item type " + clazz.getName(), e);
            }
            return items == null ? Collections.emptyList() : items;
        }
    }
}
