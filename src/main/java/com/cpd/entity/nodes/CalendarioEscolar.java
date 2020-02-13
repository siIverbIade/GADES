package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.NodeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class CalendarioEscolar extends Base {

	private int anoLetivo;

	private List<Long> calendario;

	public CalendarioEscolar(int anoLetivo, List<Long> calendario) {
		this.anoLetivo = anoLetivo;
		this.calendario=calendario;
	}
}