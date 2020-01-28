package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.Turno;
import lombok.*;

@NodeEntity
@Data
public class Turma {
	@Id @GeneratedValue Long id;

	private String nome;

	private NivelEscolar ano;

	private AnoLetivo anoLetivo;

	private Turno turno;

}