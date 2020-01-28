package com.cpd.model;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnoLetivoModel {
	
	private int nome;
	
	@NotEmpty(message ="Não existe nenhum turno selecionado.")
	private List<String> turnos;
	
	@NotEmpty(message ="Não existe nenhum dia da semana selecionado.")
	private List<String> semanas;
	
	@NotEmpty(message ="Não existe nenhum segmento selecionado.")
	private List<String> segmentos;
	
	public AnoLetivoModel(int nome,  List<String> turnos, List<String> semanas, List<String> segmentos) {
		super();
		this.nome=nome;
		this.turnos=turnos;
		this.semanas=semanas;
		this.segmentos=segmentos;
	}

	public AnoLetivoModel() {
		System.out.println("(AnoLetivoModel) Passei " + LocalDate.now());
	}
}