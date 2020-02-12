package com.cpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cpd.repository.FuncionarioRepository;

@Controller
@RequestMapping("/dme") 
public class DmeController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("")
	public String sesmec(){
		return "dme";
	}
	
	@GetMapping("/funcionario")
	public String editarFuncionario(Model model){
		model.addAttribute("Funcionario", funcionarioRepository.findAll());
		return "funcionario";
	}
}
