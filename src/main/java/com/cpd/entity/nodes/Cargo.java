package com.cpd.entity.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Cargo extends Base {

	private int cargaHoraria;
	private String classe;
	
	@EqualsAndHashCode.Exclude
	@Relationship("SERVE_AO")
	@JsonIgnore
	private Orgao orgao;

	
}