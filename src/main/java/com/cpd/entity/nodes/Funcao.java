package com.cpd.entity.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Funcao extends Base {
	
	private Integer cargaHoraria;
	
	@Relationship("LOTADO_EM")
	@JsonIgnore
	private Setor lotacao;
	
	@Relationship("HABILITA")
	@JsonIgnore
	private Grupo grupo;
}