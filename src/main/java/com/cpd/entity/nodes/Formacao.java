package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import lombok.Data;

@NodeEntity
@Data
public class Formacao {
	@Id @GeneratedValue Long id;
	private String escolaridade;
	private Boolean completo;
}
