package com.dream.service.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;


/**
 * JSON的工具类
 *
 * <h3>Here is an example:</h3>
 *
 * <pre>
 *     // 将json通过类型转换成对象
 *     {@link JsonUtil JsonUtil}.fromJson("{\"username\":\"username\", \"password\":\"password\"}", User.class);
 * </pre>
 * <hr />
 * <pre>
 *     // 传入转换的引用类型
 *     {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
 * </pre>
 * <hr />
 * <pre>
 *     // 将对象转换成json
 *     {@link JsonUtil JsonUtil}.toJson(user);
 * </pre>
 * <hr />
 * <pre>
 *     // 将对象转换成json, 可以设置输出属性
 *     {@link JsonUtil JsonUtil}.toJson(user, {@link Inclusion Inclusion.ALWAYS});
 * </pre>
 * <hr />
 * <pre>
 *     // 将对象转换成json, 传入配置对象
 *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
 *     mapper.setSerializationInclusion({@link Inclusion Inclusion.ALWAYS});
 *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
 *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
 *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
 *     {@link JsonUtil JsonUtil}.toJson(user, mapper);
 * </pre>
 * <hr />
 * <pre>
 *     // 获取Mapper对象
 *     {@link JsonUtil JsonUtil}.mapper();
 * </pre>
 *
 * @see JsonUtil JsonUtil
 * @see Feature Feature
 * @see ObjectMapper ObjectMapper
 * @see Inclusion Inclusion
 * @see IOException IOException
 * @see SimpleDateFormat SimpleDateFormat
 *
 */
@SuppressWarnings("unchecked")
public class JSONUtils {

	private static ObjectMapper MAPPER;

    static {
        MAPPER = generateMapper(Include.ALWAYS);
    }

    private JSONUtils() {
    }
    
    /**
     * 返回{@link ObjectMapper ObjectMapper}对象, 用于定制性的操作
     *
     * @return {@link ObjectMapper ObjectMapper}对象
     */
    public static ObjectMapper mapper() {
        return MAPPER;
    }

    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.fromJson("{\"username\":\"username\", \"password\":\"password\"}", User.class);
     * </pre>
     *
     * @param json json字符串
     * @param clazz 泛型类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return clazz.equals(String.class) ? (T) json : MAPPER.readValue(json, clazz);
    }

    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
     * </pre>
     *
     * @param json json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, TypeReference<?> typeReference) throws IOException {
        return (T) (typeReference.getType().equals(String.class) ? json : MAPPER.readValue(json, typeReference));
    }

    /**
     * 将对象转换成json
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.toJson(user);
     * </pre>
     *
     * @param src 对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src) throws IOException {
        return src instanceof String ? (String) src : MAPPER.writeValueAsString(src);
    }

    /**
     * 将对象转换成json, 可以设置输出属性
     *
     * <pre>
     *     {@link JsonUtil JsonUtil}.toJson(user, {@link Inclusion Inclusion.ALWAYS});
     * </pre>
     *
     * {@link Inclusion Inclusion 对象枚举}
     * <ul>
     *     <li>{@link Inclusion Inclusion.ALWAYS 全部列入}</li>
     *     <li>{@link Inclusion Inclusion.NON_DEFAULT 字段和对象默认值相同的时候不会列入}</li>
     *     <li>{@link Inclusion Inclusion.NON_EMPTY 字段为NULL或者""的时候不会列入}</li>
     *     <li>{@link Inclusion Inclusion.NON_NULL 字段为NULL时候不会列入}</li>
     * </ul>
     *
     * @param src 对象
     * @param inclusion 传入一个枚举值, 设置输出属性
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, Include include) throws IOException {
        if (src instanceof String) {
            return (String) src;
        } else {
            ObjectMapper customMapper = generateMapper(include);
            return customMapper.writeValueAsString(src);
        }
    }

    /**
     * 将对象转换成json, 传入配置对象
     *
     * <pre>
     *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
     *     mapper.setSerializationInclusion({@link Inclusion Inclusion.ALWAYS});
     *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
     *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
     *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
     *     {@link JsonUtil JsonUtil}.toJson(user, mapper);
     * </pre>
     *
     * {@link ObjectMapper ObjectMapper}
     *
     * @see ObjectMapper
     *
     * @param src 对象
     * @param mapper 配置对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, ObjectMapper mapper) throws IOException {
        if (null != mapper) {
            if (src instanceof String) {
                return (String) src;
            } else {
                return mapper.writeValueAsString(src);
            }
        } else {
            return null;
        }
    }

    /**
     * 通过Inclusion创建ObjectMapper对象
     *
     * {@link Include Include 对象枚举}
     * <ul>
     *     <li>{@link Include Include.ALWAYS 全部列入}</li>
     *     <li>{@link Include Include.NON_DEFAULT 字段和对象默认值相同的时候不会列入}</li>
     *     <li>{@link Include Include.NON_EMPTY 字段为NULL或者""的时候不会列入}</li>
     *     <li>{@link Include Include.NON_NULL 字段为NULL时候不会列入}</li>
     * </ul>
     *
     * @param inclusion 传入一个枚举值, 设置输出属性
     * @return 返回ObjectMapper对象
     */
    private static ObjectMapper generateMapper(Include include) {

        ObjectMapper objectMapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        objectMapper.setSerializationInclusion(include);

        // 所有日期格式都统一为以下样式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        
        return objectMapper;
    }
    
    public static String readValueFromJson(String json, String tag) throws Exception {  
        // 返回值  
        List<String> values = readValuesFromJson(json, tag);
        if(values != null && values.size() > 0){
            return values.get(0);
        }
        return "";  
    }
    
    /** 
     * 从json中读取tagPath处的值 tagPath用 :分隔 
     *  
     * @param json 
     * @param tagPath 
     * @return 
     * @throws Exception 
     */  
    public static List<String> readValuesFromJson(String json, String tagPath) throws Exception {  
        // 返回值  
        List<String> value = new ArrayList<String>();  
        if (isEmpty(json) || (isEmpty(tagPath))) {  
            return value;  
        }  
        String[] path = tagPath.split(":");  
        JsonNode node = MAPPER.readTree(json);  
        getJsonValue(node, path, value, 1);  
        return value;  
    }
    
    public static void getJsonValue(JsonNode node, String[] path, List<String> values, int nextIndex) {  
        if (isEmpty(node)) {  
            return;  
        }  
        // 是路径的最后就直接取值  
        if (nextIndex == path.length) {  
            if (node.isArray()) {  
                for (int i = 0; i < node.size(); i++) {  
                    JsonNode child = node.get(i).get(path[nextIndex - 1]);  
                    if (isEmpty(child)) {  
                        continue;  
                    }  
                    values.add(child.toString());  
                }  
            } else {  
                JsonNode child = node.get(path[nextIndex - 1]);  
                if (!isEmpty(child)) {  
                    values.add(child.toString());  
                }  
            }  
            return;  
        }  
        // 判断是Node下是集合还是一个节点  
        node = node.get(path[nextIndex - 1]);  
        if (node.isArray()) {  
            for (int i = 0; i < node.size(); i++) {  
                getJsonValue(node.get(i), path, values, nextIndex + 1);  
            }  
        } else {  
            getJsonValue(node, path, values, nextIndex + 1);  
        }  
    }
    
    /** 
     * 判断对象是否为空 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isEmpty(Object obj) {  
        boolean result = true;  
        if (obj == null) {  
            return true;  
        }  
        if (obj instanceof String) {  
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");  
        } else if (obj instanceof Collection) {  
            result = ((Collection) obj).size() == 0;  
        } else {  
            result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true : false;  
        }  
        return result;  
    }  
}

