package com.cpd.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.Localidade;

/*LOAD CSV FROM 'file:///municipios.csv' AS line
CREATE (:Municipio {uf: line[0], ibge: TOINTEGER(line[1]), nome: line[2]})*/
@Repository
public interface LocalidadeRepository extends Neo4jRepository<Localidade, Long>{
	
	@Query("MATCH (m:Localidade)-[r:PERTENCE_A]->(e:Estado {sigla:{0}}) RETURN m, r, e ORDER BY m.nome")
	public List<Localidade> findByUf(String uf);
	
	@Query("MATCH (m:Localidade)-[r:PERTENCE_A]->(e:Estado) WHERE Id(e)={0} RETURN m, r, e ORDER BY m.nome")
	public List<Localidade> findByUfId(Long ufid);
	
	@Query("MATCH (m:Localidade)-[r:PERTENCE_A]->(e:Estado) WHERE m.cod={0} RETURN m, r, e ORDER BY m.nome")
	public List<Localidade> findByCod(int cod);
	
}
