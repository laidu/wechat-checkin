package com.laidu.bishe.utils.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenwen on 16/10/8.
 */
@Slf4j
public class JacksonUtil {
    public JacksonUtil() {
    }

    public static <T> T jsonToObj(String json, Class<T> type) {
        try {
            ObjectMapper e = new ObjectMapper();
            e.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return e.readValue(json, type);
        } catch (JsonParseException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (JsonMappingException var4) {
            log.error("the json conversion errors", var4);
            return null;
        } catch (IOException var5) {
            log.error("the data stream error", var5);
            return null;
        }
    }

    public static <T> T jsonByLowerCaseWithUndersorcesToObj(String json, Class<T> type) {
        try {
            ObjectMapper e = new ObjectMapper();
            e.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            e.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return e.readValue(json, type);
        } catch (JsonParseException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (JsonMappingException var4) {
            log.error("the json conversion errors", var4);
            return null;
        } catch (IOException var5) {
            log.error("the data stream error", var5);
            return null;
        }
    }

    public static String objToJson(Object obj) {
        try {
            ObjectMapper e = new ObjectMapper();
            return obj == null?"\"\"":e.writeValueAsString(obj);
        } catch (JsonGenerationException var2) {
            log.error("the json conversion errors", var2);
            return null;
        } catch (JsonMappingException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (IOException var4) {
            log.error("the data stream error", var4);
            return null;
        }
    }

    public static String objToJsonByFasterXml(Object object){
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return object == null ? null : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("the json conversion errors", e);
            return null;
        }
    }

    public static <T> T jsonToObjByFasterXml(String json, Class<T> type){
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("the data stream error", e);
            return null;
        }
    }

    public static String objToJson(Object obj, DateFormat df) {
        try {
            ObjectMapper e = new ObjectMapper();
            e.setDateFormat(df);
            return obj == null?"\"\"":e.writeValueAsString(obj);
        } catch (JsonGenerationException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (JsonMappingException var4) {
            log.error("the json conversion errors", var4);
            return null;
        } catch (IOException var5) {
            log.error("the data stream error", var5);
            return null;
        }
    }

    public static JSONObject objToJSONObject(Object object) {
        return (JSONObject)jsonToObj(objToJson(object), JSONObject.class);
    }

    public static String objTOJsonWithTimeStamp(Object obj) {
        try {
            ObjectMapper e = new ObjectMapper();
            e.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
            return obj == null?"\"\"":e.writeValueAsString(obj);
        } catch (JsonGenerationException var2) {
            log.error("the json conversion errors", var2);
            return null;
        } catch (JsonMappingException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (IOException var4) {
            log.error("the data stream error", var4);
            return null;
        }
    }

    public static <T> T jsonToObjWithTimeStamp(String json, Class<T> type) {
        try {
            ObjectMapper e = new ObjectMapper();
            e.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
            e.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return e.readValue(json, type);
        } catch (JsonParseException var3) {
            log.error("the json conversion errors", var3);
            return null;
        } catch (JsonMappingException var4) {
            log.error("the json conversion errors", var4);
            return null;
        } catch (IOException var5) {
            log.error("the data stream error", var5);
            return null;
        }
    }

    public static String listToJson(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        if(list != null && list.size() != 0) {
            stringBuilder.append("[");
            int count = 0;

            Object object;
            for(Iterator var3 = list.iterator(); var3.hasNext(); stringBuilder.append(objToJson(object))) {
                object = var3.next();
                if(count++ > 0) {
                    stringBuilder.append(",");
                }
            }

            stringBuilder.append("]");
            return stringBuilder.toString();
        } else {
            stringBuilder.append("\"\"");
            return stringBuilder.toString();
        }
    }

    public static String mapToJson(Map<String, String> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();

        try {
            objectMapper.writeValue(stringWriter, map);
        } catch (IOException var4) {
            log.error("Cannot write json.", var4);
        }

        return stringWriter.toString();
    }

    public static Map<String, String> jsonToMap(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return (Map)objectMapper.readValue(json, Map.class);
        } catch (IOException var3) {
            log.error("Cannot parse json to map.", var3);
            return null;
        }
    }

    public static Map<String, Object> objectToMap(Object json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(JSON.toJSONString(json), Map.class);
        } catch (IOException var3) {
            log.error("Cannot parse json to map.", var3);
            return new HashMap<>();
        }
    }

    public static Map jsonToObjectMap(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return (Map)objectMapper.readValue(json, Map.class);
        } catch (IOException var3) {
            log.error("Cannot parse json to map.", var3);
            return null;
        }
    }
}