package com.cpd.entity.nodes;

import java.time.LocalTime;
import java.util.Date;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import lombok.*;

@RelationshipEntity("LOTADO_EM")
@Data
public class Lotacao {
	
	@Id @GeneratedValue Long id;
	
	@DateString("dd/MM/YYYY")
	private Date dataAdmissaoEscola;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private Long funcaoId;
	private String permuta;
	private String cedido;
	private String obs;
	@StartNode
	private MatriculaFuncional matriculaFuncional;
	
	@EndNode
	private Setor lotacao;
	
}