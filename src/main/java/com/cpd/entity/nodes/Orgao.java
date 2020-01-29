package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Orgao extends Base{
	private List<String> telefones;
	private String email;
	
	@Relationship("SUBORDINADO")
	private Organizacao organizacao;

	@Relationship(type="SERVE_AO", direction=Relationship.INCOMING)
	private List<Cargo> cargo;
	
	public void setTelefone1(String tel) {
		this.telefones.set(0, tel);
	}
	
	public String getTelefone1() {
		return this.telefones.get(0);
	}
}