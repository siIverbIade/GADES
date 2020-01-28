package com.cpd.entity.nodes;

import java.util.Date;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class PessoaJuridica extends Endereco {
	private String numero_cnpj;
	private String nome_fantasia;
	private String razao_social;
	
	@DateString("dd/MM/YYYY")
	private Date data_abertura;
}
