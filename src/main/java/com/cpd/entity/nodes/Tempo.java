package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.Turno;
import lombok.*;

@NodeEntity
@Data
public class Tempo {
	@Id @GeneratedValue Long id;
	
	private String nome;
	
	private Integer duracao;

	private String entrada;

	private Boolean recreio;

	private Segmento segmento;

	private Turno turno;
}