package com.cpd.entity.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class NivelEscolar extends Base {
	
	@Relationship(type="CONTEMPLA", direction = Relationship.INCOMING)
	@JsonIgnore
	private Segmento segmento;
	
	public NivelEscolar(String nome, Segmento segmento) {
		this.setNome(nome);
		this.segmento=segmento;
	}
	
	public NivelEscolar() {
	}
}