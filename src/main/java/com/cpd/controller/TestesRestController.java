package com.cpd.controller;

import com.cpd.entity.nodes.Localidade;
import com.cpd.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/////////////////INÍCIO DA DEMONSTRAÇÃO//////////////////////////

@RestController /* Desta forma declaramos a classe 'TestesRestController' como uma classe apenas
				 * de requisições 'Rest' que retorna apenas dados. Se formos criar uma api
				 * 'REST' todas as nossas classes de controladoras devem ter este padrão.
				 */
@RequestMapping("/restes")
public class TestesRestController {

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@GetMapping()
	public String urls() {
		return "<a href='/restes/testeget?'>getDados</a>";
				/* http://localhost:8080/restes -> O controlador não tenta carregar uma mesmo sem @ResponseBody anotado no método!
					Esta é a diferença de uma classe @RestController para uma @Controller
				*/
	}
	/* ALTAMENTE RECOMENDÁVEL utilizar o programa Postman */

	@GetMapping("/testeget") /* HTTP GET já conhecido para requisições */
	public String getDados(@RequestParam int cod) {
		return localidadeRepository.findByCod(cod).getNome();
		/* http://localhost:8080/restes/testepost?id=numero_id -> */
	}

	@PostMapping("/testepost") /* HTTP POST já conhecido para criação de novas instâncias */
	public Localidade postDados(@RequestBody Localidade localidade) {
		localidadeRepository.save(localidade);
		return localidade;
		/* http://localhost:8080/restes/testepost -> */
	}

	@PutMapping("/testeput") /* HTTP PUT para edição de um instância específica */
	public Localidade putDados(@RequestBody Localidade localidade) {
		Localidade mun = localidadeRepository.findByCod(localidade.getCod());
		mun = localidade;
		localidadeRepository.save(mun);
		return mun;
		/* http://localhost:8080/restes/testeput -> */
	}

	@DeleteMapping("/testedelete") /* HTTP DELETE para remoção de um instância específica */
	public String deleteDados(@RequestBody Long id) {
		localidadeRepository.deleteById(id);
		return "Localidade com id = " + id + " deletado";
		/* http://localhost:8080/restes/testedelete -> */
	}

	/* Agora mova para as classes Query e Mutation e aprenda funcionamento da API graphql */
}
