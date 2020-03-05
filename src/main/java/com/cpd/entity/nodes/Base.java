package com.cpd.entity.nodes;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.*;

@Data
public abstract class Base {
	
    @Id @GeneratedValue Long id;
    
    private String nome;
    @CreatedDate
    @DateLong
    @JsonIgnore
    private Date dataCriacao;
        
    @LastModifiedDate
    @DateLong
    @JsonIgnore
    private Date dataModificado;

    private Boolean enabled;

}