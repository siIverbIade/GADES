package com.cpd.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cpd.entity.nodes.Grupo;
import com.cpd.model.GrupoModel;
import com.cpd.repository.GrupoRepository;

@Service
@Transactional
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	/**CONSULTA OS GRUPOS CADASTRADOS*/
	@Transactional(readOnly = true)
	public List<GrupoModel> consultarGrupos(){

		List<GrupoModel> gruposModel =  new ArrayList<GrupoModel>();

		/*CONSULTA TODOS OS GRUPOS*/
		Iterable<Grupo> gruposEntity = this.grupoRepository.findAll();
		
	    gruposEntity.forEach(grupo ->{ 
	    	gruposModel.add(new GrupoModel(grupo.getId(), grupo.getDescricao())); 
	    });

		return gruposModel;
	}
}
