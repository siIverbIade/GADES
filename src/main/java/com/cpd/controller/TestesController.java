package com.cpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cpd.model.User;
import com.cpd.repository.CurrentDB;
import com.cpd.utils.Debug;

@Controller /* Este Bean indica que esta classe serve como um controlador para gerenciar requisições http da aplicação. */
@RequestMapping("/testes") /* diz que esta classe está funcionando no endereço base 'http://localhost:8080/testes/...etc' */
public class TestesController {

	@GetMapping() /* este método do tipo 'HTTP GET (Enviar ao cliente)' disponível na url http://localhost:8080/testes no navegador*/
	public String carregarNaRaiz() {
		return "Google2";/* O spring vai procurar um template de nome 'Google2.html' na pasta do projeto
							 * '../resources/templates' e como não vai encontrar ocorrerá o erro HTTP 500:
							 * 
							 * "...Request processing failed; nested exception is
							 * org.thymeleaf.exceptions.TemplateInputException: Error resolving template
							 * [Google2], template might not exist or might not be accessible by any of the
							 * configured Template Resolvers..."
							 */
	}

	@GetMapping("/pagina") /* este método do tipo 'HTTP GET (Enviar ao cliente)' disponível na url http://localhost:8080/testes/pagina */
	public String carregarPagina() {
		return "Google"; /* agora a página carrega! */
	}

	@GetMapping("/conteudo")
	@ResponseBody /*esta anotação faz com que o endereço http://localhost:8080/testes/conteudo apenas retorne o valor da função */
	public String carregarConteudo() {
		return "Google"; /* repare que mesmo a função 'carregarConteudo' sendo idêntica a
							 * 'carregarPagina' desta vez aperece apenas a palavra Google no navegador
							 * graças ao @ResponseBody */
	}

	@GetMapping("/listagem") /* http://localhost:8080/testes/listagem */
	public String carregarLista(Model model) { /* o parâmetro Model é utilizado para povoar a página com objetos Java. Este é o padrão de aplicações web chamado de 'MVC - Model and View Controller' */
		List<String> lista = new ArrayList<String>(); /* criamos uma lista qualquer para demonstrar objetos java e o conceito do Spring MVC */

		lista.add("primeiro da lista");
		lista.add("segundo da lista");
		lista.add("terceiro da lista");

		model.addAttribute("ObjetoLista", lista); /* aqui foi exportado o objeto 'lista' sob o nome de 'ObjetoLista', que será o nome que deve ser informado no template '../templates/teste_formulario.html' */
		model.addAttribute("NomeDaLista", "Lista qualquer"); /* Parâmetro com o nome que vai entrar no cabeçalho */
		return "teste_formulario"; /* este formulário está formatado como um exemplo simplificado de integração com
									  o java utilizando a engine 'Thymeleaf' para renderizar o model. */
	}


	@GetMapping("/formulario") /* http://localhost:8080/testes/formulario */
	public String carregarFormulario(Model model) { 

		model.addAttribute("ObjetoSubmeter", new User()); /* Objeto User de exemplo que tem os campos login e senha */

		return "teste_formulario_submeter";
	}

	@PostMapping("/formulario") /* veja que este método por ser do tipo 'HTTP POST (Receber do cliente)' não pode ser executado
								 na url http://localhost:8080/testes/formulario diretamente pelo navegador. 
								 
								 Vai dar o seguinte erro: "..Request method 'GET' not supported.." */

	public String submeterFormulario(@ModelAttribute("ObjetoSubmeter") User obj) {
		Debug.Print(obj); /* imprime no console o objeto de nome 'ObjetoSubmetido' que foi passado na página */
		return "teste_formulario_submeter";
	}

	@Autowired
	private CurrentDB db; /* Exemplo de Injeção de dependência que será utilizada no método 'testarCypher'
							 * abaixo. Resumidamente, anotação '@Autowired' indica para o spring que ele
							 * deve gerenciar automaticamente o instanciamento em tempo de execução desta
							 * propriedade (assim como a anotação '@Bean'), mas além disso ela permite a
							 * chamada de outras sub-dependências que podem ser necessárias ao seu funcionamento, sejam elas Beans, Component, Service ou
							 * Repository.
							 */

	@GetMapping("/cypher")
	public String testarCypher(Model model) {
		List<Map<String, Object>> consulta = db.cypher("MATCH (n:Localidade) RETURN n", "n"); /* lista de municípios */
		
		model.addAttribute("ObjetoLista", consulta);
		model.addAttribute("NomeDaLista", "Lista de Municípios");
		return "teste_formulario"; /* a flexibilidade é tanta que deu pra reutilizar o mesmo formulário utilizado em 'carregarLista' */
	}

	@GetMapping("/parametros")
	@ResponseBody
	public String pegarParametros(@RequestParam(required = false) String param1,
			@RequestParam String param2) { 
		return "Oi você passou parâmetro1 = " + param1 + " e parâmetro2 = " + param2;
	} /* a @RequestParam antes de uma variável no argumento significa que podemos passar parâmetros na url http://localhost:8080/testes/parametros 
		para esta variável. 
		Ex. a variável param1 é passada acessando http://localhost:8080/testes/parametros?param1=valor1
			como a variavel param2 não tem (required = false) e por padrão required = true você terá erro HTTP 400 (Bad Request)
			e a seguinte mensagem: '...Required String parameter 'param2' is not present...'.
			Assim esses dois são possíveis:
			http://localhost:8080/testes/parametros?param2=valor2 -> (retorna "Oi você passou parâmetro1 = null e parâmetro2 = valor2")
				OU
			http://localhost:8080/testes/parametros?param1=valor1&param2=valor2 -> (retorna "Oi você passou parâmetro1 = valor1 e parâmetro2 = valor2")

		*/
}
