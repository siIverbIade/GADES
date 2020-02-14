package com.cpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cpd.entity.nodes.Estado;
import com.cpd.model.EstadoModel;
import com.cpd.model.User;
import com.cpd.repository.CurrentDB;
import com.cpd.repository.EstadoRepository;
import com.cpd.utils.Debug;

/////////////////INÍCIO DA DEMONSTRAÇÃO//////////////////////////

@Controller /* Este Bean indica que esta classe serve como um controlador para gerenciar requisições http da aplicação. */
@RequestMapping("/testes") /* (opcional) diz que esta classe está funcionando no endereço base'http://localhost:8080/testes/...etc'.*/
public class TestesController {

	@GetMapping() /*
					 * este método do tipo 'HTTP GET (Enviar ao cliente)' disponível na url
					 * http://localhost:8080/testes no navegador
					 */
	public String carregarNaRaiz() {
		return "Google2";/*
							 * O spring vai procurar um template de nome 'Google2.html' na pasta do projeto
							 * '../resources/templates' e como não vai encontrar ocorrerá o erro HTTP 500:
							 * 
							 * "...Request processing failed; nested exception is
							 * org.thymeleaf.exceptions.TemplateInputException: Error resolving template
							 * [Google2], template might not exist or might not be accessible by any of the
							 * configured Template Resolvers..."
							 */
	}

	@GetMapping("/pagina") /* este método do tipo 'HTTP GET (Enviar ao cliente)' disponível na url http://localhost:8080/testes/pagina. 
							 * OBS: Na ausência de @RequestMapping na definição da classe, o caminho declarado deve ser completo '/testes/pagina' */
	public String carregarPagina() {
		return "Google"; /* agora a página carrega! */
	}

	@GetMapping("/conteudo")
	@ResponseBody /* esta anotação faz com que o endereço http://localhost:8080/testes/conteudo apenas retorne o valor da função */
	public String carregarConteudo() {
		return "Google"; /* repare que mesmo a função 'carregarConteudo' sendo idêntica a 'carregarPagina' desta vez aperece apenas a palavra Google no navegador graças ao @ResponseBody */
	}

	@GetMapping("/listagem") /* http://localhost:8080/testes/listagem */
	public String carregarLista(Model model) { /* o parâmetro Model é utilizado para povoar a página com objetos Java. Este é o
							 * padrão de aplicações web chamado de 'MVC - Model and View Controller' */
		List<String> lista = new ArrayList<String>(); /* criamos uma lista qualquer para demonstrar objetos java e o conceito do Spring MVC */

		lista.add("primeiro da lista");
		lista.add("segundo da lista");
		lista.add("terceiro da lista");

		model.addAttribute("ObjetoLista",lista); /* aqui foi exportado o objeto 'lista' sob o nome de 'ObjetoLista', que será o
						 * nome que deve ser informado no template '../templates/teste_formulario.html' */
		model.addAttribute("NomeDaLista", "Lista qualquer"); /* Parâmetro com o nome que vai entrar no cabeçalho */
		return "teste_formulario"; /* este formulário está formatado como um exemplo simplificado de integração com
									 * o java utilizando a engine 'Thymeleaf' para renderizar o model. */
	}

	@GetMapping("/formulario") /* http://localhost:8080/testes/formulario */
	public String carregarFormulario(Model model) {

		model.addAttribute("ObjetoSubmeter", new User()); /* Objeto User de exemplo que tem os campos login e senha */

		return "teste_formulario_submeter";
	}

	@PostMapping("/formulario") /* OBS: métodos do tipo 'HTTP POST (Receber do cliente)' não podem ser executados diretamente pelo navegador. Vai dar o seguinte erro: "..Request method 'GET' not supported.." */
	public String submeterFormulario(@ModelAttribute("ObjetoSubmeter") User obj) {
		Debug.Print(obj); /* imprime no console o objeto de nome 'ObjetoSubmetido' que foi passado na página */
		return "teste_formulario_submeter";
	}

	@GetMapping("/cypher")
	public String testarCypher(Model model) {
		CurrentDB db = new CurrentDB();
		List<Map<String, Object>> consulta = db.loadCypher("MATCH (n:Localidade) RETURN n",
				"n"); /* lista de municípios obtida usando query cypher diretamente do banco neo4j via driver.v1 básico */

		model.addAttribute("ObjetoLista", consulta);
		model.addAttribute("NomeDaLista", "Lista de Municípios");
		return "teste_formulario"; /* a flexibilidade é tanta que dá pra reutilizar o mesmo formulário utilizado em 'carregarLista'*/
	}

	@Autowired
	private EstadoRepository estadoRepository; /*
												 * Exemplo de Injeção de dependência que será utilizada no método
												 * 'testarSpringData' abaixo. Resumidamente, anotação '@Autowired'
												 * indica para o spring que ele deve gerenciar automaticamente o
												 * instanciamento em 'tempo de requisição' desta propriedade (assim como
												 * a anotação '@Bean'), mas além disso ela permite a chamada de outras
												 * sub-dependências que podem ser necessárias ao seu funcionamento,
												 * sejam elas Beans, Component, Service ou Repository. Explicação
												 * detalhada em https://www.baeldung.com/spring-bean
												 */

	@GetMapping("/springdata/{nome}") /* padrão para se declarar uma variável de caminho, adiciona-se '/{nome_da_variavel}'. Ex http://localhost:8080/testes/springdata/Rio */
	public String testarSpringData(Model model,
			@PathVariable String nome) {/* @PathVariable deve corresponder ao que foi declarado em @GetMapping(value), neste caso a variavel 'nome' */
		List<EstadoModel> estado = new ArrayList<EstadoModel>();

		if (nome == "todos") {
			Iterable<Estado> estados = estadoRepository
					.findAll(); /* Exemplo de busca utilizando repositório Spring data com método padrão */
			List<EstadoModel> est = new ArrayList<EstadoModel>();
			estados.forEach(e -> {
				est.add(new EstadoModel(e));
			});
			model.addAttribute("ObjetoLista", est);
		} else {
			estado = estadoRepository.encontrarPeloNome(nome);/* Exemplo de busca utilizando repositório Spring data com método customuzado. EstadoModel é uma versão simplificada de Estado (verifique a classe) */
			model.addAttribute("ObjetoLista", estado);
		}
		model.addAttribute("NomeDaLista", "Lista de Estados com nome contendo \"" + nome + "\"");
		return "teste_formulario";
	}

	@GetMapping("/parametros")
	@ResponseBody
	public String pegarParametros(@RequestParam(required = false) String param1, @RequestParam String param2) {
		return "Oi você passou parâmetro1 = " + param1 + " e parâmetro2 = " + param2;
	} /*
		 * a @RequestParam antes de uma variável no argumento significa que podemos
		 * passar parâmetros na url http://localhost:8080/testes/parametros para esta
		 * variável. Ex. a variável param1 é passada acessando
		 * http://localhost:8080/testes/parametros?param1=valor1 mas como a variavel
		 * param2 não tem (required = false) e por padrão required = true, você terá
		 * erro HTTP 400 (Bad Request) e a seguinte mensagem: '...Required String
		 * parameter 'param2' is not present...' pois a variavel param2 é obrigatória.
		 * 
		 * Agora tente esses dois links:
		 * http://localhost:8080/testes/parametros?param2=valor2 -> (retorna
		 * "Oi você passou parâmetro1 = null e parâmetro2 = valor2") OU
		 * http://localhost:8080/testes/parametros?param1=valor1&param2=valor2 ->
		 * (retorna "Oi você passou parâmetro1 = valor1 e parâmetro2 = valor2") */

	@GetMapping("/httpstatus")
	ResponseEntity<String> httpResponseEntityExemplo(@RequestParam("httpOk") boolean bool) {/* 'httpOK' sobrescreve o nome padrão que seria 'bool' */
		/* Aqui vamos manipular para criar um 'handle' para manipular os códigos de erro http dentro do próprio 
			controlador atraves da interface ResponseEntity<T>. Para verificar a resposta http de uma página no google chrome clique com botão direito na página -> inspecionar ->
			network: depois aperte F5 e veja o status que ela retorna.
		*/
		if (!bool) {
			/* http://localhost:8080/testes/httpstatus?httpOk=false
			ResponseEntity retorna http error code 400 */
			return new ResponseEntity<>("Mensagem de HTTP bad request", HttpStatus.BAD_REQUEST);
		}
		/* http://localhost:8080/testes/httpstatus?httpOk=true
		ResponseEntity retorna http error code 200 */
		return new ResponseEntity<>("Mensagem de HTTP ok", HttpStatus.OK);

	}

	@GetMapping(value = "/google")
	public String redirectToGoogle(@RequestParam(required = false) boolean fake) {
		if (fake) {
			return "redirect:/testes/pagina"; /* podemos redirecionar para outro mapeamento: http://localhost:8080/testes/google fica equivalente a http://localhost:8080/testes/pagina */
		} else {
			return "redirect:http://www.google.com.br"; /* ou podemos redirecionar para servidor externo http://localhost:8080/testes/google?fake=false */
		}
	}

	@GetMapping(value = "/combobox")
	public String carregarDroplistEstado(Model model) {
		model.addAttribute("Estado", estadoRepository.findAllByPais("Brasil"));
		return "fragments/select :: selectOption"; /* http://localhost:8080/testes/combobox retorna apenas o 
		fragmento html contido em /fragments/select.html, mas apenas o que tiver dentro da tag
		marcada com th:fragment="selectOption". Isto é muito útil para ser usado com
		requisições ajax ou jquery &(object).load("/testes/combobox") para carregamento dinâmico.

		Acesse http://localhost:8080/teste e clique em Carregar Droplist */
	}
}
