package com.cpd.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.neo4j.annotation.QueryResult;
import lombok.*;

@Data
@QueryResult
public class Labeled {
    private String tabela;
    private Map<String, Object> campo_valor = new HashMap<String, Object>();
}