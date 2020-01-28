package com.cpd.entity.nodes;

import java.util.List;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import com.cpd.entity.arrows.VinculoProf;
import com.cpd.type.DiaSemana;
import com.cpd.type.Turno;
import lombok.Data;

@NodeEntity
@Data
public class AnoLetivo {
	@Id @GeneratedValue Long  id;
	
	private int nome;
	
	private List<Turno> turnos;
	
	private List<DiaSemana> semanas;
	
	@Relationship("TEM")
	private List<Segmento> segmentos;
	
	@Relationship(type="VINCULO", direction = Relationship.UNDIRECTED)
	private List<VinculoProf> professoresvinculados;
	
	public AnoLetivo(int nome, List<Turno> turnos, List<DiaSemana> semanas, List<Segmento> segmentos) {
		this.nome=nome;
		this.turnos=turnos;
		this.semanas=semanas;
		this.segmentos=segmentos;
	}
	
	public AnoLetivo() {
		
	}
}