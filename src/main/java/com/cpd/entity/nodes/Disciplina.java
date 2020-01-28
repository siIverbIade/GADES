package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;

@NodeEntity
@Data
public class Disciplina {
	@Id @GeneratedValue Long id;
	private String nome;
	
	private String sigla;

	@Relationship("PERTENCE_AO")
	private Segmento segmento;

	private Integer temposSemanais;

}