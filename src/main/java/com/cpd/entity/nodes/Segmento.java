package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.*;

@NodeEntity
@Data
public class Segmento {
	@Id @GeneratedValue Long id;
	
	private String nome;
	
	private Integer totais;
	
	@Relationship("CONTEMPLA")
	private List<NivelEscolar> anos = new ArrayList<>();
}