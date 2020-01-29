package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Permissao extends Base {
	
	private String descricao;
	
	private String permissao;
	
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	@Relationship(type="CONCEDE_ACESSO", direction=Relationship.INCOMING)
    private List<Grupo> grupo = new ArrayList<>();
   
}
