package com.cpd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.cpd.entity.nodes.MatriculaFuncional;
import com.cpd.entity.nodes.Pessoa;

@Repository
public interface MatriculaRepository extends Neo4jRepository<MatriculaFuncional, Long>{
	
	public Pessoa findFuncionarioByMatricula(int matricula);

	public MatriculaFuncional findByMatricula(int matricula);
	
}
