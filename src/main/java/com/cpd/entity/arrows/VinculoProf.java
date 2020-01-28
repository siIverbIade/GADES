package com.cpd.entity.arrows;

import com.cpd.entity.nodes.AnoLetivo;
import com.cpd.entity.nodes.Professor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import lombok.*;

@RelationshipEntity
@Data
public class VinculoProf {
	@Id @GeneratedValue Long id;

	@StartNode
	private Professor professor;
	
	@EndNode
	private AnoLetivo anoLetivo;

	private String semanaTempo;

	private Integer temposSemanais;

}