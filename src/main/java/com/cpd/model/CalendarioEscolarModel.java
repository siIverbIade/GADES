package com.cpd.model;

import java.time.LocalDate;
import java.util.List;
import com.cpd.type.MesesAno;
import com.cpd.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.data.neo4j.annotation.QueryResult;

import lombok.Data;

//POJO para calendario_escolar.html
@Data
@QueryResult
public class CalendarioEscolarModel {

	private int anoLetivo;

	private int mes;

	private List<RotuloCalendarioModel> cal;

	public CalendarioEscolarModel(int anoLetivo, int mes, List<RotuloCalendarioModel> cal) {

		this.anoLetivo = anoLetivo;
		this.mes = mes;
		this.cal = cal;

		System.out.println("(CalendarioEscolarModel) Passei " + LocalDate.now());
	}

	public String getDay(int f) {
		DateTime dataRef = DateUtils.getData(1, mes, anoLetivo);
		int primeiroDiaSemana = dataRef.getDayOfWeek() % 7;
		int ultimoDiaMes = dataRef.dayOfMonth().withMaximumValue().getDayOfMonth();
		f++;
		if ((f > primeiroDiaSemana) && (f <= ultimoDiaMes + primeiroDiaSemana)) {
			return Integer.toString(f - primeiroDiaSemana);
		} else {
			return "";
		}

	}

	public String getNome(int f){
		return cal.get(f).getNome();
	}

	public String getMes() {
		return MesesAno.values()[mes - 1].FormatoCompleto();
	}

	public String corDeFundo(int f) {
		return ((cal.get(f).getBackColor() == null) ? "" : cal.get(f).getBackColor());
	}

	public String corDeFrente(int f) {

		return ((cal.get(f).getForeColor() == null) ? "" : cal.get(f).getForeColor());
	}
}