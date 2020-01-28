package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
public class Funcao {
	
	@Id @GeneratedValue Long id;
	
	private String nome;
	private Integer cargaHoraria;
	
	@Relationship("LOTADO_EM")
	private Setor lotacao;
	
	@Relationship("HABILITA")
	private Grupo grupo;
}