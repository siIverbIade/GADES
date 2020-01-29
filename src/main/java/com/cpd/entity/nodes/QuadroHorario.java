package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.DiaSemana;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class QuadroHorario extends Base {
	
	private DiaSemana semana;
	
	private Tempo tempo;
	
	private Turma turma;

	private Professor professor;

	private Disciplina disciplina;
	
}