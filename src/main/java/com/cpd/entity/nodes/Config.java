package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@NodeEntity
@Data
public class Config {
	@Id @GeneratedValue Long id;
	
	private Boolean firstRun;
	
	private Long escolaInep;
	
	private int anoLetivo;
	
	private String ultimaAtualizacao;

	public void setConfig(Config conf) {
		if (conf.firstRun != null) {
			this.firstRun=conf.firstRun;
		}
		if (conf.escolaInep != null) {
			this.escolaInep=conf.escolaInep;
		}
	}
}