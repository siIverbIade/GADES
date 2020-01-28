package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NodeEntity
@Data
public class Grupo {
	@Id @GeneratedValue Long id;
	
	private String nome;
	
	private String descricao;
	
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	@Relationship(type="PERTENCE_A", direction=Relationship.INCOMING)
	private List<Usuario> usuario = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@Relationship("CONCEDE_ACESSO")
	private List<Permissao> permissao = new ArrayList<>();


}
