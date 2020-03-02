package com.cpd.entity.nodes;

import java.util.Date;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import lombok.Data;

@NodeEntity
@Data
public class Config {
	@Id @GeneratedValue Long id;
	
	private Boolean firstRun;
	
	private Long escolaInep;
	
	private int anoLetivo;
	
	@DateString("dd/MM/YYYY")
	private Date ultimaAtualizacao;

	public void setConfig(Config conf) {
		if (conf.firstRun != null) {
			this.firstRun=conf.firstRun;
		}
		if (conf.escolaInep != null) {
			this.escolaInep=conf.escolaInep;
		}
	}
}