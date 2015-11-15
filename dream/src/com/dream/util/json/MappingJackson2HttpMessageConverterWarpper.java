package com.dream.util.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 包装MappingJackson2HttpMessageConverter 复写 writeInternal方法
 * <p/>
 * 增加输出json数据日志
 */
public class MappingJackson2HttpMessageConverterWarpper extends MappingJackson2HttpMessageConverter {

    private static ObjectMapper objectMapper = new CustomObjectMapper();

    protected final Logger log = LoggerFactory.getLogger(MappingJackson2HttpMessageConverterWarpper.class);

    // 默认是false
    private boolean prefixJson = false;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        MediaType contentType = outputMessage.getHeaders()
						.getContentType();
		JsonEncoding encoding = getJsonEncoding(contentType);
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
        if(contentType==null||contentType.isCompatibleWith(MediaType.APPLICATION_JSON)||contentType.toString().contains("application/json")){
//        	objectMapper.writeValueAsString(object);
        	objectMapper.writeValue(jsonGenerator, ((MappingJacksonValue)object).getValue());
        	return;
        }

        // A workaround for JsonGenerators not applying serialization features
        // https://github.com/FasterXML/jackson-databind/issues/12

        if (objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }

        try {

            /**
             *
             *
             */
            if (this.prefixJson) {
                jsonGenerator.writeRaw("{} && ");
            }
            /**
             *
             * 增加日志输出功能
             */
            if (log.isDebugEnabled()) {
                log.debug("return json : {}", objectMapper.writeValueAsString(object));
            }
            objectMapper.writeValue(jsonGenerator, object);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    /**
     * Indicate whether the JSON output by this view should be prefixed with
     * "{} &&". Default is false.
     * <p/>
     * Prefixing the JSON string in this manner is used to help prevent JSON
     * Hijacking. The prefix renders the string syntactically invalid as a
     * script so that it cannot be hijacked. This prefix does not affect the
     * evaluation of JSON, but if JSON validation is performed on the string,
     * the prefix would need to be ignored.
     */
    public void setPrefixJson(boolean prefixJson) {
        this.prefixJson = prefixJson;
    }
    

}
