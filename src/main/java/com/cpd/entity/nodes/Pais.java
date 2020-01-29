package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Pais extends Base {

	private int cod;
	private String nacionalidade;
	private String sigla;
	
	@Relationship(type="PERTENCE_A", direction=Relationship.INCOMING)
	private List<Estado> estados;
	
	@Relationship(type="CAPITAL", direction=Relationship.INCOMING)
	private Localidade capital;
}