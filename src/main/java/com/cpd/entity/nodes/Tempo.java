package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.Turno;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Tempo extends Base {
	
	private Integer duracao;

	private String entrada;

	private Boolean recreio;

	private Segmento segmento;

	private Turno turno;
}