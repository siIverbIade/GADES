package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.NodeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Formacao extends Base {
	private String escolaridade;
	private Boolean completo;
}
