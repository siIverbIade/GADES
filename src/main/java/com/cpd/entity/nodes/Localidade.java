package com.cpd.entity.nodes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* LOAD CSV FROM 'file:///file.csv' AS line
CREATE (:Tabela {campo1: line[0], campo2: line[1], campo3: line[2], num: TOINTEGER(line[3])})
*/

@NodeEntity
@Data
@EqualsAndHashCode(callSuper=false)
public class Localidade extends Base {

	private int cod;
	
	@EqualsAndHashCode.Exclude
	@Relationship("PERTENCE_A")
	@JsonIgnore
	private Estado estado;

	@JsonGetter("estado")
    public Long getTheEstado() {
        return this.estado.getId();
	}
}
