package com.cpd.apigql;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpd.entity.nodes.NivelEscolar;
import com.cpd.entity.nodes.AnoLetivo;
import com.cpd.repository.AnoLetivoRepository;
import com.cpd.repository.NivelEscolarRepository;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.annotations.GraphQLMutation;

@Service
@GraphQLApi
public class Mutation {
	
	@Autowired
	private NivelEscolarRepository anoRepository;
	
	@Autowired
	private AnoLetivoRepository anoLetivoRepository;
	
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
	public void novoAnoLetivo(AnoLetivo anoLetivo) {
		List<String> segLst = new ArrayList<>();
		anoLetivo.getSegmentos().forEach(s -> segLst.add(s.getNome()));
		anoLetivoRepository.salvarAnoLetivo(anoLetivo.getNome(), anoLetivo.getSemanas(), anoLetivo.getTurnos(), segLst);
	}
	
	@GraphQLMutation
	public AnoLetivo editarAnoLetivo(AnoLetivo anoLetivo) {
		anoLetivoRepository.save(anoLetivo);
		return anoLetivo;
	}
	
	@GraphQLMutation
	public boolean deletarAno(NivelEscolar ano) {
		anoRepository.delete(ano);
		return true;
	}

	@GraphQLMutation
	public boolean deletarAnoLetivo(AnoLetivo anoLetivo) {
		anoLetivoRepository.delete(anoLetivo);
		return true;
	}
	
}