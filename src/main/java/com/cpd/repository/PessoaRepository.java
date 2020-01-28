package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Pessoa;

@Repository
public interface PessoaRepository extends Neo4jRepository<Pessoa, Long> {
}