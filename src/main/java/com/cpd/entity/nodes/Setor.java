package com.cpd.entity.nodes;

import java.util.List;
import java.util.stream.Stream;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.cpd.entity.arrows.MatriculaEstudantil;
import com.cpd.model.EscolaModel;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class Setor extends PessoaJuridica {

	private String email;
	private String celular;
	private Long inep;

	@EqualsAndHashCode.Exclude
	@Relationship(type = "MATRIZ", direction = Relationship.INCOMING)
	private Organizacao organizacao;

	private List<String> telefones;

	@EqualsAndHashCode.Exclude
	@Relationship(type = "MATRICULADO", direction = Relationship.INCOMING)
	private List<MatriculaEstudantil> alunos;

	@EqualsAndHashCode.Exclude
	@Relationship(type = "LOTADO_EM", direction = Relationship.INCOMING)
	private List<Lotacao> funcionarios;

	public void setEscola(EscolaModel esc) {
		this.setNumero(esc.getNumero());
		this.setLogradouro(esc.getLogradouro());
		this.setBairro(esc.getBairro());
		this.setComplemento(esc.getComplemento());
		this.setCep(esc.getCep());
		this.setNumero_cnpj(esc.getCnpj());
		this.setNome(esc.getNome());
		this.celular = esc.getCelular();
		this.inep = esc.getInep();
		this.telefones = esc.getTelefones();
	}
	
	public Stream<Lotacao> getByFuncaoId(Long funcaoId) {
		return this.funcionarios.stream().filter(e -> e.getFuncaoId().equals(funcaoId));
	}
	
	public void setTelefone1(String tel) {
		this.telefones.set(0, tel);
	}

	public String getTelefone1() {
		if (this.telefones.get(0) == null) {
			return "";
		} else {
			return this.telefones.get(0);
		}
	}

}