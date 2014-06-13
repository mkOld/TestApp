package com.excilys.testapp.android.websocket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by hrandr on 13/06/14.
 */
public class JacksonMapper {

    public <T> T toJavaObject(String text, Class<T> type) {

        ObjectMapper mapper = new ObjectMapper();
        final Class<T> c = type;
        T t = null;

        try {
            t = mapper.readValue(text, c);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    public <T> String toJSON(T t) {

        String result = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.writeValueAsString(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
