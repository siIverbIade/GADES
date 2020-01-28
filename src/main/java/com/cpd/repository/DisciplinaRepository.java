package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Disciplina;

@Repository
public interface DisciplinaRepository extends Neo4jRepository<Disciplina, Long>{

}
