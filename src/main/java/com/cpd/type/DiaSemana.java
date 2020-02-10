package com.cpd.type;

import com.cpd.utils.TextUtils;

public enum DiaSemana {
	SEG("Seg","Segunda", "Segunda-Feira"), TER("Ter","Terça", "Terça-Feira"),
	QUA("Qua","Quarta", "Quarta-Feira"), QUI("Qui","Quinta", "Quinta-Feira"), 
	SEX("Sex","Sexta", "Sexta-Feira"), SAB("Sáb","Sábado", "Sábado"), 
	DOM("Dom","Domingo", "Domingo");
	
	private final String text_short;
	private final String text;
	private final String text_full;
	
	DiaSemana(String text_short,String text, String text_full) {
		this.text_short = text_short;
        this.text = text;
        this.text_full = text_full;
	}
	
	DiaSemana() {
		this.text_short = "";
		this.text="";
		this.text_full = "";
	}

	public String FormatoReduzido() {
		return (text_short == "") ? this.toString() : text_short;
    }

	public String FormatoSimples() {
		return (text == "") ? this.toString() : text;
    }
	
	public String FormatoCompleto() {
		return (text_full == "") ? this.toString() : text_full;
    }
	
	public String FormatoAbreviado() {
		return TextUtils.firstLetterUp(this.toString());
	}
}