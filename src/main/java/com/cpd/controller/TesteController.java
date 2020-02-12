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
import com.cpd.model.EscolaModel;
import com.cpd.repository.CurrentDB;

@Controller
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CurrentDB db;

	@GetMapping("")
	public String carregarNaRaiz(){
		return "Google";
	}

	@GetMapping("/pagina")
	public String carregarPagina(){
		return "Google";
	}

	@GetMapping("/conteudo")
	@ResponseBody
	public String carregarConteudo(){
		return "Google";
	}

	@GetMapping("/formulario")
	public String carregarFormulario(Model model) {
		List<String> lista = new ArrayList<String>();
		lista.add("primeiro da lista");
		lista.add("segundo da lista");
		lista.add("terceiro da lista");
		model.addAttribute("Objeto", lista);
		return "teste";
	}
	@PostMapping("/submeter")
	@ResponseBody
	public void submeterFormulario(@ModelAttribute("Objeto") EscolaModel escolaModel) {
	}

	@GetMapping("/cypher")
	public String testarCypher(Model model) {
		List<Map<String, Object>> resultado = db.cypher("MATCH (n:Localidade) RETURN n", "n");
		model.addAttribute("Objeto", resultado);
		return "teste";
	}

	@GetMapping("/parametros")
	@ResponseBody
	public String loadCalendarioEscolarMes(@RequestParam(required = true) String param1,
			@RequestParam(required = true) String param2) {
		return "Oi você passou parâmetro1 = " + param1 + " e parâmetro2 = " + param2;
	}
}
