package com.cpd.model;

import java.time.LocalDate;
import com.cpd.entity.nodes.RotuloCalendario;
import org.springframework.data.neo4j.annotation.QueryResult;
import lombok.Data;

@Data
@QueryResult
public class RotuloCalendarioModel {

    private boolean temAula;
    private String simbolo;
    private String nome;
    private String descricao;
    private String foreColor;
    private String backColor;
    
    public RotuloCalendarioModel() {

    }

    public RotuloCalendarioModel(RotuloCalendario rotuloCalendario) {
        this.simbolo = rotuloCalendario.getSimbolo();
        this.nome = rotuloCalendario.getNome();
        this.descricao = rotuloCalendario.getDescricao();
        this.foreColor = rotuloCalendario.getForeColor();
        this.backColor = rotuloCalendario.getBackColor();
        this.temAula = rotuloCalendario.isTemAula();
        System.out.println("(RotuloCalendarioModel) Passei " + LocalDate.now());
    }
}