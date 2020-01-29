package com.cpd.entity.nodes;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Usuario extends Base {
	
	private String login;
	
	@GraphQLIgnore
	private String senha;
	
	private boolean ativo;
	
	@EqualsAndHashCode.Exclude
	@Relationship("PERTENCE_A")
	private List<Grupo> grupo = new ArrayList<>();
	
	@EqualsAndHashCode.Exclude
	@Relationship("ASSOCIADO_A")
	private MatriculaFuncional matricula;

}
