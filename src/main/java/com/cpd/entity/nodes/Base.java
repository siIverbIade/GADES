package com.cpd.entity.nodes;

import java.util.Date;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.*;

@Data
public abstract class Base {
	
    @Id @GeneratedValue Long id;
	
    private String nome;
    
    @CreatedDate
    @DateString("dd/MM/YYYY")
    private Date dataCriacao;
        
    @LastModifiedDate
    @DateString("dd/MM/YYYY")
    private Date dataModificado;
}