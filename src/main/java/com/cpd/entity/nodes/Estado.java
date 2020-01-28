package com.cpd.entity.nodes;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* LOAD CSV FROM 'file:///file.csv' AS line
CREATE (:Tabela {campo1: line[0], campo2: line[1], campo3: line[2], num: TOINTEGER(line[3])})

*/

@NodeEntity
@Data
public class Estado {
	@Id @GeneratedValue Long id;
	private int cod;
	private String nome;
	private String sigla;
	
	@EqualsAndHashCode.Exclude
	@Relationship("PERTENCE_A")
	private Pais pais;
	
	@EqualsAndHashCode.Exclude
	@Relationship(type="PERTENCE_A", direction=Relationship.INCOMING)
	private List<Localidade> municipios;
	
}
