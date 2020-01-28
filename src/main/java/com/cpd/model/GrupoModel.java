package com.cpd.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class GrupoModel {
 
	private Long id;
	private String nome;	
	private String descricao;
	private Boolean checked;
 
	public GrupoModel(){
		System.out.println("(GrupoModel) Passei " + LocalDate.now());
	}
 
	public GrupoModel(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	public GrupoModel(Long id,String descricao) {
		super();
		this.id = id;	
		this.descricao = descricao;
	}
}