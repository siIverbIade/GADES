package com.cpd.entity.arrows;

import java.time.LocalTime;
import java.util.Date;

import com.cpd.entity.nodes.MatriculaFuncional;
import com.cpd.entity.nodes.Setor;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import lombok.Data;

@RelationshipEntity("REGIME_ESPECIAL")
@Data
public class Ret {
	
	@Id @GeneratedValue Long id;
	
	@DateString("dd/MM/YYYY")
	private Date dataInicio;
	
	@DateString("dd/MM/YYYY")
	private Date dataFim;
	
	private LocalTime horaInicio;
	private int horasExtras;
	
	@StartNode
	private MatriculaFuncional matriculaFuncional;
	
	@EndNode
	private Setor lotacao;
	
	public LocalTime getHoraFim() {
		return horaInicio.plusMinutes(horasExtras);
	}
}