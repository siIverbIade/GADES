package com.cpd.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
    ObjectMapper mapper = new ObjectMapper();
    public String Decode(Object object, boolean wrapper) {
        
        if (wrapper) mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        try {
            System.out.println(mapper.writeValueAsString(object));
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}