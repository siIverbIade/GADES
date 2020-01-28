package com.cpd.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.cpd.entity.nodes.Setor;
import com.cpd.entity.nodes.Lotacao;

@Repository
public interface LotacaoRepository extends Neo4jRepository<Lotacao, Long>{
	
	public List<Lotacao> findAllByFuncaoIdAndLotacao(Long funcaoId, Setor lotacao, @Depth int depth);
	
	@Query("MATCH (m:MatriculaFuncional), (e:Setor) WHERE Id(m)={matriculaId} AND Id(e)={setorId} OPTIONAL MATCH (m)-[k:LOTADO_EM]->(:Setor) DELETE k WITH m, e CREATE (m)-[:LOTADO_EM {funcaoId:{funcaoId}}]->(e)")
	public void setLotacao(Long matriculaId, Long setorId, Long funcaoId);
	
	@Query("MATCH (:MatriculaFuncional)-[k:LOTADO_EM {funcaoId:{funcaoId}}]->(e:Setor) WHERE Id(e)={setorId} DELETE k")
	public void removeLotacao(Long setorId, Long funcaoId);
	
}
