package com.cpd.model;

import java.time.LocalDate;
import java.util.List;
import com.cpd.entity.nodes.Organizacao;
import lombok.Data;

//POJO para index.html
@Data
public class OrganizacaoModel {
 
	private Long id;
	private String nome;
	private String cnpj;
	private Long localidadeId;
	private Long ufId;
	private String celular;
	private Long inep;
	private List<String> telefones;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cep;
	
	public OrganizacaoModel(){
		System.out.println("(EscolaModel) Passei " + LocalDate.now());
	}
 
	public OrganizacaoModel(Organizacao org) {
		super();
		this.id = org.getId();
		this.nome = org.getNome();
		this.localidadeId = org.getLocalidade().getId();
		this.ufId = org.getLocalidade().getEstado().getId();
		this.celular = org.getCelular();
		this.cnpj = org.getNumero_cnpj();
		this.telefones = org.getTelefones();
		this.logradouro = org.getLogradouro();
		this.numero = org.getNumero();
		this.bairro = org.getBairro();
		this.complemento = org.getComplemento();
		this.cep = org.getCep();
	}
}