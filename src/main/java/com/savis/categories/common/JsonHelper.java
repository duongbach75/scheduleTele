package com.savis.categories.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class);

    public static Gson gson = new Gson();

    /**
     * convert a string to object
     *
     * @param json
     *            the string from which the object is to be deserialized
     * @param the
     *            class of T
     * @return an object of type T from the string, may be null
     */
    public static <T> T jsonToObject(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;

    }

    public static Map<String, Object> jsonToMap(String json) {
        try {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;

    }

}
