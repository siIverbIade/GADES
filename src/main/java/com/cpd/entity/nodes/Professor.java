package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Professor extends Base {

	private String apelido;
	
	@Relationship("LECIONA")
	private List<Disciplina> disciplina;
	
}