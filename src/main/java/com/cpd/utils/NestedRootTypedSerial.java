package com.cpd.utils;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NestedRootTypedSerial {
    public static String getSerial(final Object obj){
        final ObjectMapper mapper = new ObjectMapper();
        if (obj instanceof List<?> || obj instanceof Iterable<?>) {
            
            final List<Object> al = new ArrayList<Object>();
            @SuppressWarnings("unchecked")
            List<Object> objList = (List<Object>) obj;
            objList.forEach(e ->{
                final JsonWrapper jwl = new JsonWrapper();
                jwl.getTypes().put(e.getClass().getSimpleName(), e);
                al.add(jwl);
            });
            try {
                return mapper.writeValueAsString(al);
            } catch (final JsonProcessingException e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            final JsonWrapper jw = new JsonWrapper();
            jw.getTypes().put(obj.getClass().getSimpleName(), obj);
            try {
                return mapper.writeValueAsString(jw);
            } catch (final JsonProcessingException e) {
                System.out.println(e.getMessage());
                return "";
            }
        }
    }
}