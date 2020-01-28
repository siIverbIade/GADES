package com.cpd.entity.nodes;

import java.util.Date;

import com.cpd.entity.nodes.OrgExp;

import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class PessoaFisica extends Endereco {
	private Long numero_rg;
	private Long numero_cpf;
	
	@DateString("dd/MM/YYYY")
	private Date data_emissao_rg;
	
	@DateString("dd/MM/YYYY")
	private Date validade_rg;
	
	@Relationship("ORGAO_EXPEDITOR")
	private OrgExp org_exp_rg;
}
