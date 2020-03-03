package com.cpd.utils;

import com.cpd.type.DiaSemana;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

public final class DateUtils {

	public static DateTime now(){
		return new DateTime(DateTimeZone.UTC);
	}

	public static DateTime getData(int ano, int dias) {
		DateTime dtOrg = new DateTime(ano, 01, 01, 00, 00, 00, DateTimeZone.forID("GMT"));
		DateTime dtPlusDias = dtOrg.plusDays(dias);
		return dtPlusDias;
	}

	public static DateTime getString(String data){
		return DateTime.parse(data, DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public static DateTime getData(int dia, int mes, int ano) {
		DateTime dtOrg = new DateTime(ano, mes, dia, 00, 00, 00, DateTimeZone.forID("GMT"));
		return dtOrg;
	}

	public static int getDiasDoAno(int dia, int mes, int ano) {
		DateTime dtOrg = new DateTime(ano, mes, dia, 00, 00, 00, DateTimeZone.forID("GMT"));
		return dtOrg.getDayOfYear();
	}

	public static int getDia(int ano, int dias) {
		return getData(ano,  dias).getDayOfMonth();
	}
	
	public static int getDia(int dia, int mes, int ano) {
		return getData(dia, mes, ano).getDayOfMonth();
	}

	public static int getMes(int ano, int dias) {
		return getData(ano,  dias).getMonthOfYear();
	}
	
	public static int getMes(int dia, int mes, int ano) {
		return getData(dia, mes, ano).getMonthOfYear();
	}

    public static DiaSemana getSemana(int ano, int dias) {
		return DiaSemana.values()[getData(ano,  dias).getDayOfWeek()-1];
	}

    public static DiaSemana getSemana(int dia, int mes, int ano) {
		return DiaSemana.values()[getData(dia, mes, ano).getDayOfWeek()-1];
	}

}