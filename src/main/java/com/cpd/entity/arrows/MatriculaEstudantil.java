package com.cpd.entity.arrows;

import com.cpd.entity.nodes.Pessoa;
import com.cpd.entity.nodes.Turma;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import lombok.*;

@RelationshipEntity("MATRICULADO")
@Data
public class MatriculaEstudantil {
	@Id @GeneratedValue Long id;
	
	private int matricula;
	
	@StartNode
	private Pessoa aluno;
	
	@EndNode
	private Turma escola;
}