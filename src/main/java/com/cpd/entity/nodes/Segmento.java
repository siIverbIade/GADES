package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Segmento extends Base {
	
	private Integer totais;
	
	@Relationship("CONTEMPLA")
	private List<NivelEscolar> anos = new ArrayList<>();
}