package com.cpd.entity.nodes;

import java.util.Date;
import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import com.cpd.type.Etnia;
import com.cpd.type.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Pessoa extends PessoaFisica {
	
	@DateString("dd/MM/YYYY")
	private Date nascimento;
	private Sexo sexo;
	private Etnia cor;
	
	@Relationship("TEM_SANGUE")
	@JsonIgnore
	private TipoSang tiposang;
	private String email;
	private String situacao;
	private String obs;
	private List<String> telefones;
	private String cursos;
	
	@Relationship(type="PAI", direction = Relationship.INCOMING)
	@JsonIgnore
	private Pessoa pai;
	
	@Relationship(type="MAE", direction = Relationship.INCOMING)
	@JsonIgnore
	private Pessoa mae;
	
	@Relationship("NATIVO_EM")
	@JsonIgnore
	private Localidade naturalidade;
	
	@Relationship("CIDADAO_DE")
	@JsonIgnore
	private Pais nacionalidade;
	
	@Relationship("ESCOLARIDADE")
	@JsonIgnore
	private Formacao formacao;

}