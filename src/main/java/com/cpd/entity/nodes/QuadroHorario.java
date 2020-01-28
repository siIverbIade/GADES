package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.DiaSemana;
import lombok.*;

@NodeEntity
@Data
public class QuadroHorario {
	@Id @GeneratedValue Long id;
	
	private DiaSemana semana;
	
	private Tempo tempo;
	
	private Turma turma;

	private Professor professor;

	private Disciplina disciplina;
	
}