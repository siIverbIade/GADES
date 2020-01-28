package com.cpd.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SemanaModel {
 
	private String nome;	
	private Boolean checked;
 
	public SemanaModel(){
		System.out.println("(SemanaModel) Passei " + LocalDate.now());
	}
 
	public SemanaModel(String nome) {
		super();
		this.nome = nome;
	}
}