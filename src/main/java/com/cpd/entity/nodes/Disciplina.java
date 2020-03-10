package com.cpd.entity.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Disciplina extends Base {
	
	private String sigla;

	@Relationship("PERTENCE_AO")
	@JsonIgnore
	private Segmento segmento;

	private Integer temposSemanais;

}