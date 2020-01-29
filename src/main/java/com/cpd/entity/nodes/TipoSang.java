package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.cpd.type.NomeTs;
import com.cpd.type.RhTs;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class TipoSang extends Base {

	private RhTs rh;
	private NomeTs tipo;
	
	@Relationship(type="PODE_DOAR", direction = Relationship.INCOMING)
	private List<TipoSang> doadores;
	
	@Relationship(type="PODE_DOAR", direction = Relationship.OUTGOING)
	private List<TipoSang> receptores;
	
}