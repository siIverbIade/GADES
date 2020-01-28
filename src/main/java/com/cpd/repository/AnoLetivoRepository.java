package com.cpd.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.AnoLetivo;
import com.cpd.type.DiaSemana;
import com.cpd.type.Turno;

@Repository
public interface AnoLetivoRepository extends Neo4jRepository<AnoLetivo, Long> {
	
	public AnoLetivo findByNome(int nome);
	
	@Query("MATCH (a:AnoLetivo) OPTIONAL MATCH (a)-[r]-(b) RETURN a, r, b ORDER BY a.nome")
	public List<AnoLetivo> findAllOrderByNome(@Depth int depth);
	
	@Query("MERGE (a:AnoLetivo {nome: {0}}) WITH a OPTIONAL MATCH (a)-[r:TEM]->(:Segmento) DELETE r WITH a MATCH (s:Segmento) WHERE s.nome IN {3} MERGE (a)-[:TEM]->(s) SET a.semanas={1}, a.turnos={2}")
	public void salvarAnoLetivo(int nome, List<DiaSemana> semanas, List<Turno> turnos, List<String> segmentosNome);
	
}
