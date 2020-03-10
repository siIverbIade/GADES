package com.cpd.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JsonWrapper {
    @JsonIgnore
    public String type;

    Map<String, Object> types = new HashMap<>();
    
    @JsonAnyGetter
    public Map<String, Object> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Object> types) {
        this.types = types;
    }
}