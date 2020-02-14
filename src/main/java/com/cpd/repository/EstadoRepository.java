package com.cpd.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Estado;
import com.cpd.model.EstadoModel;

/*LOAD CSV FROM 'file:///municipios.csv' AS line
CREATE (:Municipio {uf: line[0], ibge: TOINTEGER(line[1]), nome: line[2]})*/

@Repository
public interface EstadoRepository extends Neo4jRepository<Estado, Long>{
	@Query("MATCH (e:Estado)-[:PERTENCE_A]->(:Pais {nome:{0}}) RETURN e ORDER BY e.nome")
	public List<Estado> findAllByPais(String nomePais);

	@Query("MATCH (e:Estado) WHERE lower(e.nome) CONTAINS lower({nome}) RETURN e.nome as nome, e.cod as cod, e.sigla as sigla") 
	/* EstadoModel com @QueryResult pode receber os campos de 'RETURN' */
	public List<EstadoModel> encontrarPeloNome(String nome);
}
