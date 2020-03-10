package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Segmento extends Base {
	
	private Integer totais;
	
	@Relationship("CONTEMPLA")
	@JsonIgnore
	private List<NivelEscolar> anos = new ArrayList<>();
}