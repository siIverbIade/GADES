package com.cpd.apigql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpd.entity.nodes.NivelEscolar;
import com.cpd.repository.NivelEscolarRepository;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.annotations.GraphQLMutation;

@Service
@GraphQLApi
public class Mutation {
	
	@Autowired
	private NivelEscolarRepository anoRepository;
	
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