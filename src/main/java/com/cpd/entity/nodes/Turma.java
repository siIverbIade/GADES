package com.cpd.entity.nodes;

import org.neo4j.ogm.annotation.NodeEntity;
import com.cpd.type.Turno;
import lombok.*;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Turma extends Base {

	private NivelEscolar ano;

	private Turno turno;

}