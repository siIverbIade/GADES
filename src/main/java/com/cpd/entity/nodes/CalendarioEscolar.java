package com.cpd.entity.nodes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import com.cpd.type.DiaSemana;
import com.cpd.utils.DateUtils;
import org.neo4j.ogm.annotation.NodeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class CalendarioEscolar extends Base {

	private int anoLetivo;

	private List<Long> calendario = Arrays.asList(new Long[366]);

	public CalendarioEscolar(int anoLetivo, Long diaNormalId, Long sabadoId, Long domingoId) {
		this.anoLetivo = anoLetivo;
		IntStream.range(0, 366).forEach(i -> {
			calendario.set(i, diaNormalId);
			
			if (DateUtils.getSemana(anoLetivo, i).equals(DiaSemana.SAB)) {
				calendario.set(i, sabadoId);
			}

			if (DateUtils.getSemana(anoLetivo, i).equals(DiaSemana.DOM)) {
				calendario.set(i, domingoId);
			}
			
		});
	}
}