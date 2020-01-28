package com.cpd.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Depth;
//import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Grupo;

@Repository
public interface GrupoRepository extends Neo4jRepository<Grupo, Long>{
	//@Query("MATCH (u:Usuario)-[:PERTENCE_A]->(g:Grupo) WHERE Id(u)={0} RETURN g")
	public List<Grupo> findByUsuarioIn(Long usuarioId, @Depth int depth);
}
