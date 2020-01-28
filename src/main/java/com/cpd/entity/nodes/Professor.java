package com.cpd.entity.nodes;

import java.util.List;

import com.cpd.entity.arrows.VinculoProf;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
public class Professor {
	@Id @GeneratedValue Long id;
	
	private String apelido;
	
	@Relationship("LECIONA")
	private List<Disciplina> disciplina;
	
	@Relationship(type="VINCULO", direction = Relationship.UNDIRECTED)
	private List<VinculoProf> vinculoletivo;
}