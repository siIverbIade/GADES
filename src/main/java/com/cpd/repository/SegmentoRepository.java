package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Segmento;

@Repository
public interface SegmentoRepository extends Neo4jRepository<Segmento, Long>{
	public Segmento findByNome(String nome);
}
