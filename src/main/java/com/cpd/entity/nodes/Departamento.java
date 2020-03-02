package com.cpd.entity.nodes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Departamento extends Base{

	private String sigla;
	private List<String> telefones;
	private String email;
	
	@Relationship("SUBORDINADO")
	@JsonIgnore
	private Orgao orgao;
	
	@Relationship(type="SERVE_AO", direction=Relationship.INCOMING)
	@JsonIgnore
	private List<Cargo> cargo;
	
	public void setTelefone1(String tel) {
		this.telefones.set(0, tel);
	}
	
	public String getTelefone1() {
		return this.telefones.get(0);
	}
}