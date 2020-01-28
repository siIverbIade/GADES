package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;

@NodeEntity
@Data
public class NivelEscolar {
	@Id @GeneratedValue Long id;
	private String nome;
	
	@Relationship(type="CONTEMPLA", direction = Relationship.INCOMING)
	private Segmento segmento;
	
	public NivelEscolar(String nome, Segmento segmento) {
		this.nome = nome;
		this.segmento=segmento;
	}
	
	public NivelEscolar() {
	}
}