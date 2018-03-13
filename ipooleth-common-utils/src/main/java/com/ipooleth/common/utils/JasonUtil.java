package com.ipooleth.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by abc123 on 17/4/24.
 */

public abstract class JasonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static String SPACE = "&nbsp;";

    public JasonUtil() {
    }

    public static String toJson(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = MAPPER.getFactory().createGenerator(baos, JsonEncoding.UTF8);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonGenerator.writeObject(obj);
        String json = baos.toString();
        baos.close();
        return json;
    }

    public static String mapToJson(Map<String, ?> map) throws IOException {
        String json = MAPPER.writeValueAsString(map);
        return json;
    }

    public static Map<String, Object> jsonToMap(String json) throws JsonParseException, JsonMappingException, IOException {
        Map map = (Map)MAPPER.readValue(json, new TypeReference<Map<String, Object>>() {

        });
        return map;
    }
    public static boolean isEmpty(String arg) {
        return arg == null || arg.trim().length() == 0;
    }

    public static <T> T fromJson(String jason, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        if(!isEmpty(jason) && clazz != null) {
            Object obj = MAPPER.readValue(jason, clazz);
            return (T)obj;
        } else {
            return null;
        }
    }

    public static <T extends Collection<?>> T jasonToCollection(String src, Class<T> collectionClass, Class... valueType) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClass, valueType);
        return (T) MAPPER.readValue(src, javaType);
    }

    public static String toJson(String obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = MAPPER.getFactory().createGenerator(baos, JsonEncoding.UTF8);
        jsonGenerator.writeObject(obj);
        String json = baos.toString();
        baos.close();
        return json;
    }

    public static String formatJson(String json) {
        if(json == null) {
            return "json不能为空";
        } else {
            StringBuffer result = new StringBuffer();
            int length = json.length();
            int number = 0;
            boolean key = false;

            for(int i = 0; i < length; ++i) {
                char var6 = json.charAt(i);
                if(var6 != 91 && var6 != 123) {
                    if(var6 != 93 && var6 != 125) {
                        if(var6 == 44) {
                            result.append(var6);
                            result.append("<br/>");
                            result.append(indent(number));
                        } else {
                            result.append(var6);
                        }
                    } else {
                        result.append("<br/>");
                        --number;
                        result.append(indent(number));
                        result.append(var6);
                        if(i + 1 < length && json.charAt(i + 1) != 44) {
                            result.append("<br/>");
                        }
                    }
                } else {
                    if(i - 1 > 0 && json.charAt(i - 1) == 58) {
                        result.append("<br/>");
                        result.append(indent(number));
                    }

                    result.append(var6);
                    result.append("<br/>");
                    ++number;
                    result.append(indent(number));
                }
            }

            return result.toString();
        }
    }

    private static String indent(int number) {
        StringBuffer result = new StringBuffer();

        for(int i = 0; i < number * 5 + 2; ++i) {
            result.append(SPACE);
        }

        return result.toString();
    }


    public static void main(String[] args) throws Exception {

//        WebParam shop = new WebParam();
//        shop.setData("123");
//        shop.setMethod("456");
//
//        String a = toJson(shop);
//        System.out.println(a);
//        Map<String, Object> map = JasonUtil.jsonToMap(a);
//        System.out.println(map.get("data"));
//        WebParam df = JasonUtil.fromJson(a,WebParam.class);
//        System.out.println(df.getData());
//
//        List<WebParam> list0 =new ArrayList<>();
//        list0.add(shop);
//
//        String json = JasonUtil.toJson(list0);
//        List<WebParam> list1= JasonUtil.jasonToCollection(json,List.class,WebParam.class);
//        System.out.println(list1.get(0).getData());

    }

}
