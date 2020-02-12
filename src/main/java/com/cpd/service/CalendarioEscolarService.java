package com.cpd.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import com.cpd.entity.nodes.CalendarioEscolar;
import com.cpd.entity.nodes.RotuloCalendario;
import com.cpd.model.RotuloCalendarioModel;
import com.cpd.repository.CalendarioEscolarRepository;
import com.cpd.repository.RotuloRepository;
import com.cpd.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//POJO para escola.html
@Component
public class CalendarioEscolarService {

	@Autowired
	private CalendarioEscolarRepository calendarioEscolarRepository;

	@Autowired
	private RotuloRepository rotuloRepository;

	public CalendarioEscolar newCal(int anoLetivo) {
		List<RotuloCalendario> rcList = rotuloRepository.findByGlobal(true);
		List<Long> cal = Arrays.asList(new Long[366]);
		Collections.fill(cal, (long) 31452);

		rcList.forEach(global -> {

			IntStream.range(1, 1 + global.getTotalDates(anoLetivo)).forEach(i -> {
				try {
					cal.set(DateUtils.getString(global.getDates(anoLetivo, i)).getDayOfYear() - 1, global.getId());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			});
		});

		CalendarioEscolar calendario = calendarioEscolarRepository.findByAnoLetivo(anoLetivo)
				.orElse(new CalendarioEscolar(anoLetivo, cal));
		calendarioEscolarRepository.save(calendario);
		return calendario;
	}

	public CalendarioEscolar editarRotuloDia(int anoLetivo, int diaInicial, int sequencia, Long id) {
		CalendarioEscolar calendarioEscolar = calendarioEscolarRepository.findByAnoLetivo(anoLetivo).get();
		Collections.fill(calendarioEscolar.getCalendario().subList(diaInicial - 1, diaInicial + sequencia - 1), id);
		calendarioEscolarRepository.save(calendarioEscolar);
		return calendarioEscolar;
	}

	public RotuloCalendario getRotulo(int dia, int anoLetivo) {
		CalendarioEscolar calendarioEscolar = calendarioEscolarRepository.findByAnoLetivo(anoLetivo).get();
		return rotuloRepository.findById(calendarioEscolar.getCalendario().get(dia - 1))
				.orElse(rotuloRepository.findByNome("Dia de aula normal"));
	}

	public void setRotulo(int dia, int anoLetivo, RotuloCalendario rotulo) {
		CalendarioEscolar calendarioEscolar = calendarioEscolarRepository.findByAnoLetivo(anoLetivo).get();
		calendarioEscolar.getCalendario().set(dia - 1, rotulo.getId());
	}

	public RotuloCalendario getRotulo(int dia, int mes, int anoLetivo) {
		CalendarioEscolar calendarioEscolar = calendarioEscolarRepository.findByAnoLetivo(anoLetivo).get();
		return rotuloRepository
				.findById(calendarioEscolar.getCalendario().get(DateUtils.getDiasDoAno(dia, mes, anoLetivo) - 1))
				.orElse(rotuloRepository.findByNome("Dia de aula normal"));
	}

	public void setRotulo(int dia, int mes, int anoLetivo, RotuloCalendario rotulo) {
		CalendarioEscolar calendarioEscolar = calendarioEscolarRepository.findByAnoLetivo(anoLetivo).get();
		calendarioEscolar.getCalendario().set(DateUtils.getDiasDoAno(dia, mes, anoLetivo) - 1, rotulo.getId());
	}

	public List<RotuloCalendarioModel> getListaRotulo(int mes, int anoLetivo) {
		List<RotuloCalendarioModel> list = rotuloRepository.rotulosAnoLetivo(anoLetivo);
		List<RotuloCalendarioModel> rotList = new ArrayList<RotuloCalendarioModel>();

		DateTime dataRef = DateUtils.getData(1, mes, anoLetivo);
		int primeiroDiaSemana = dataRef.getDayOfWeek() % 7;
		int ultimoDiaMes = dataRef.dayOfMonth().withMaximumValue().getDayOfMonth();
		IntStream.range(1, 43).forEach(i -> {

			if ((i > primeiroDiaSemana) && (i <= ultimoDiaMes + primeiroDiaSemana)) {
				int indice = DateUtils.getDiasDoAno(i - primeiroDiaSemana, mes, anoLetivo);
				rotList.add(list.get(indice - 1));
			} else {
				rotList.add(new RotuloCalendarioModel());
			}

		});
		return rotList;
	}

	public List<String> calendario(int anoLetivo) {
		String numlist = calendarioEscolarRepository.ListaRotulos(anoLetivo);
		String str[] = numlist.split(",");
		List<String> cal = Arrays.asList(str);

		return cal;
	}

}