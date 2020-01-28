package com.cpd.entity.nodes;

import com.cpd.entity.nodes.Localidade;
import org.neo4j.ogm.annotation.Relationship;
import lombok.*;

@Data
public abstract class Endereco {
	
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cep;
	
	@EqualsAndHashCode.Exclude
	@Relationship("SITUA_SE")
	private Localidade localidade;
}