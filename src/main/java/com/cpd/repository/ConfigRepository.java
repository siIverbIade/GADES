package com.cpd.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Config;

@Repository
public interface ConfigRepository extends Neo4jRepository<Config, Long>{
	@Query("MATCH (i:Config) RETURN i")
	public Config get();
}
