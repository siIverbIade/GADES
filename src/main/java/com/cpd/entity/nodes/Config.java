package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;

@NodeEntity
@Data
public class Config {
	@Id @GeneratedValue Long id;
	private Boolean firstRun;
	
	private Long escolaInep;
	
	@Relationship(type="ESTAMOS_EM")
	private AnoLetivo anoLetivo;
	
	public void setConfig(Config conf) {
		if (conf.firstRun != null) {
			this.firstRun=conf.firstRun;
		}
		if (conf.escolaInep != null) {
			this.escolaInep=conf.escolaInep;
		}
	}
}