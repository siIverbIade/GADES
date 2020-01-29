package com.cpd.entity.nodes;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Funcao extends Base {
	
	private Integer cargaHoraria;
	
	@Relationship("LOTADO_EM")
	private Setor lotacao;
	
	@Relationship("HABILITA")
	private Grupo grupo;
}