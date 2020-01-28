package com.cpd.entity.nodes;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.cpd.type.NomeTs;
import com.cpd.type.RhTs;
import lombok.*;

@NodeEntity
@Data
public class TipoSang {
	@Id @GeneratedValue Long id;
	private NomeTs nome;
	private RhTs rh;
	
	@Relationship(type="PODE_DOAR", direction = Relationship.INCOMING)
	private List<TipoSang> doadores;
	
	@Relationship(type="PODE_DOAR", direction = Relationship.OUTGOING)
	private List<TipoSang> receptores;
	
}