package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.NivelEscolar;

@Repository
public interface NivelEscolarRepository extends Neo4jRepository<NivelEscolar, Long>{
}
