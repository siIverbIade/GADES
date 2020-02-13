package com.cpd.apigql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.annotations.GraphQLMutation;
import com.cpd.entity.nodes.*;
import com.cpd.repository.*;
import com.cpd.service.CalendarioEscolarService;

@Component
@GraphQLApi
public class Mutation {

	@Autowired
	private NivelEscolarRepository anoRepository;

	@Autowired
	private CalendarioEscolarService clendarioEscolarService;

	@Autowired
	private RotuloRepository rotuloRepository;

	@GraphQLMutation
	public RotuloCalendario rotuloNew(String nome) {
		RotuloCalendario rc = new RotuloCalendario(nome, false, false, "", "", "white", "red", "");
		rotuloRepository.save(rc);
		return rc;
	}

	@GraphQLMutation
	public String rotuloSetSingleDate(String nome, int dia, int mes) {
		RotuloCalendario rc = rotuloRepository.findByNome(nome);
		rc.setSingleDate(dia, mes);
		rotuloRepository.save(rc);
		return rc.getJsRegra();
	}

	@GraphQLMutation
	public CalendarioEscolar newCal(int anoLetivo) {
		return clendarioEscolarService.newCal(anoLetivo);
	}

	@GraphQLMutation
	public CalendarioEscolar editarRotuloDia(int anoLetivo, int diaInicial, int sequencia, Long id) {
		return clendarioEscolarService.editarRotuloDia(anoLetivo, diaInicial, sequencia, id);
	}

	@GraphQLMutation
	public NivelEscolar editarAno(NivelEscolar ano) {
		anoRepository.save(ano);
		return ano;
	}

	@GraphQLMutation
	public NivelEscolar novoAno(NivelEscolar ano) {
		anoRepository.save(ano);
		return ano;
	}

	@GraphQLMutation
	public boolean deletarAno(NivelEscolar ano) {
		anoRepository.delete(ano);
		return true;
	}

}