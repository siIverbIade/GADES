package com.cpd.model;

import com.cpd.entity.nodes.Estado;

import org.springframework.data.neo4j.annotation.QueryResult;
import lombok.Data;

@Data
@QueryResult /* esta anotação permite que o repositório spring data retorne o objeto */
public class EstadoModel {

    private int cod;
    private String nome;
	private String sigla;

    public EstadoModel(){}

    public EstadoModel(Estado estado){
        this.cod = estado.getCod();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }
}