package com.dream.util.json;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Json工具类
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static final CustomObjectMapper mapper;

    static {
        mapper = new CustomObjectMapper();
    }

    /**
     * json字符串转换为对象
     *
     * @param str
     * @param clazz
     * @return T
     */
    public static <T> T stringToObject(String str, Class<T> clazz) {
        T o = null;
        try {
            o = mapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * map转换为json字符串
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Object> map) {
        String str = "";
        try {
            str = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static JsonNode getJsonNode(String json) {
        JsonNode node = null;
        try {
            node = mapper.readTree(json);
        } catch (Exception e) {
            log.error("Unable to serialize to json: ", e);
        }
        return node;
    }

    public static String toJSONString(Object object) {
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, object);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unable to serialize to json: " + object, e);
            return null;
        }
        return writer.toString();
    }

    public static <T> T parse(String json, Class<T> type) {
        T object;
        try {
            object = mapper.readValue(json, type);
        } catch (RuntimeException e) {
            log.error("Runtime exception during deserializing ");
            throw e;
        } catch (Exception e) {
            log.error("exception during deserializing[" + json + "]", e);
            return null;
        }
        return object;
    }

    public static List<Map<String, Object>> toArray(JsonParser jp) {
        //Assert.notNull(jp);
        try {
            return mapper.readValue(jp, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            log.error("Runtime exception ", e);
        }
        return null;
    }

    public static <T> List<T> toArray(JsonParser jp, Class<T> type) {
        try {
            return mapper.readValue(jp, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            log.error("Runtime exception ", e);
        }
        return null;
    }

    public static List<Map<String, Object>> toArray(String jsonArrayString) {
        try {
            return mapper.readValue(jsonArrayString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            log.error("Runtime exception", e);
        }
        return null;
    }


}
