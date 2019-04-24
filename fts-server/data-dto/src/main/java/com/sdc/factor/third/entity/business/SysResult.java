package com.sdc.factor.third.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.Serializable;

/**
 * 调用第三方api返回数据结构
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysResult implements Serializable {
    private static final long serialVersionUID = -2503673262883699709L;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Integer errorCode;
    private String description;
    private Object data;

    public static SysResult build(Integer errorCode, String description, Object data) {
        return new SysResult(errorCode, description, data);
    }

    public static SysResult oK(Object data) {
        return new SysResult(data);
    }

    public static SysResult oK() {
        return new SysResult(null);
    }

    public static SysResult build(Integer errorCode, String description) {
        return new SysResult(errorCode, description, null);
    }

    public SysResult(Object data) {
        this.errorCode = 0000;
        this.description = "查询成功";
        this.data = data;
    }
    public Boolean isOk() {
        return this.errorCode == 0000;
    }

/**
 *
 *  将json结果集转化为SysResult对象
 *  @param jsonData json数据
 *  @param clazz SysResult中的object类型
 *  @return
 *
 */

    public static SysResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, SysResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("errorCode").intValue(), jsonNode.get("description").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static SysResult format(String json) {
        try {
            return MAPPER.readValue(json, SysResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
