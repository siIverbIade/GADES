package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
public class Pais {
	
	@Id @GeneratedValue Long id;
	private int cod;
	private String nome;
	private String nacionalidade;
	private String sigla;
	
	@Relationship(type="PERTENCE_A", direction=Relationship.INCOMING)
	private List<Estado> estados;
	
	@Relationship(type="CAPITAL", direction=Relationship.INCOMING)
	private Localidade capital;
}