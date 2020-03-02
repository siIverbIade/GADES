package com.cpd.entity.nodes;

import java.util.Date;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import com.cpd.entity.arrows.Ret;
import com.cpd.type.Vinculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class MatriculaFuncional extends Base {
	
	private int matricula;
	
	@DateString("dd/MM/YYYY")
	private Date dataAdmissao;
	
	@DateString("dd/MM/YYYY")
	private Date dataExoneracao;
	
	private Vinculo vinculo;
	
	@EqualsAndHashCode.Exclude
	@Relationship(type="TRABALHA_COM", direction = Relationship.INCOMING)
	@JsonIgnore
	private Pessoa funcionario;
	
	@EqualsAndHashCode.Exclude
	@Relationship("OCUPA")
	@JsonIgnore
	private Cargo cargo;
	
	@EqualsAndHashCode.Exclude
	@Relationship("LOTADO_EM")
	@JsonIgnore
	private Lotacao movimentacao;
	
	@EqualsAndHashCode.Exclude
	@Relationship("REGIME_ESPECIAL")
	@JsonIgnore
	private Ret ret;

}