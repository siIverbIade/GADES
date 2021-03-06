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
public class Grupo extends Base {
	
	private String descricao;
	
	@EqualsAndHashCode.Exclude
	@Relationship(type="PERTENCE_A", direction=Relationship.INCOMING)
	@JsonIgnore
	private List<Usuario> usuario = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@Relationship("CONCEDE_ACESSO")
	@JsonIgnore
	private List<Permissao> permissao = new ArrayList<>();


}
