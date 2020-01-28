package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Funcao;

@Repository
public interface FuncaoRepository extends Neo4jRepository<Funcao, Long>{
	
	public Funcao findByNome(String nome);
}
