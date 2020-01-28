package com.cpd.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Organizacao;

@Repository
public interface OrganizacaoRepository extends Neo4jRepository<Organizacao, Long>{

	@Query("MATCH (o:Organizacao) RETURN o")
	public Organizacao get();
	
	public List<Organizacao> findAll(@Depth int depth);
}
