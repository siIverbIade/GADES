package com.cpd.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Setor;
import com.cpd.type.TipoSetor;

@Repository
public interface SetorRepository extends Neo4jRepository<Setor, Long>{
	@Query("MERGE (i:Setor {inep:{0}, tipo:{1}}) RETURN i")
	public Setor merge(long inep, TipoSetor tipo);
	
	public Setor findByInep(long l, @Depth int depth);
	
	@Query("MATCH (s:Setor) WHERE s.inep IS NOT NULL RETURN s")
	public List<Setor> findAllEscolas();
}
