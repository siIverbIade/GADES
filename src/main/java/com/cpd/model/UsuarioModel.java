package com.cpd.model;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
public class UsuarioModel {
 
	private Long id;
	
	@NotNull(message ="É obrigatório selecionar um funcionário.")
	private Long funcionarioId;
	
	private String nomeFuncionario;
	
	@NotEmpty(message ="O Login é de preenchimento obrigatório.")
	private String login;
 
	@NotEmpty(message ="A Senha é de preenchimento obrigatório.")
	private String senha;
 
	private boolean ativo;
 
	@NotEmpty(message ="Não existe nenhum grupo selecionado.")
	private  List<Long> grupos; 
 
	public UsuarioModel(){
		System.out.println("(UserModel) Passei " + LocalDate.now());
	}
 
	public UsuarioModel(Long id, Long pessoaId, String nomePessoa, String login, String senha, boolean ativo, List<Long> grupos) {
		super();
		this.setId(id);
		this.setFuncionarioId(pessoaId);
		this.setNomeFuncionario(nomePessoa);
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.grupos = grupos;
	}
 
}