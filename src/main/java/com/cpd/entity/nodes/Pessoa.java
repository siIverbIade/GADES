package com.cpd.entity.nodes;

import java.util.Date;
import java.util.List;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import com.cpd.type.Etnia;
import com.cpd.type.Sexo;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Pessoa extends PessoaFisica {
	
	@Id @GeneratedValue Long id;
	
	private String nome;
	
	@DateString("dd/MM/YYYY")
	private Date nascimento;
	private Sexo sexo;
	private Etnia cor;
	
	@Relationship("TEM_SANGUE")
	private TipoSang tiposang;
	private String email;
	private String situacao;
	private String obs;
	private List<String> telefones;
	private String cursos;
	
	@Relationship(type="PAI", direction = Relationship.INCOMING)
	private Pessoa pai;
	
	@Relationship(type="MAE", direction = Relationship.INCOMING)
	private Pessoa mae;
	
	@Relationship("NATIVO_EM")
	private Localidade naturalidade;
	
	@Relationship("CIDADAO_DE")
	private Pais nacionalidade;
	
	@Relationship("ESCOLARIDADE")
	private Formacao formacao;

}