package com.cpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cpd.entity.nodes.Organizacao;
import com.cpd.model.OrganizacaoModel;
import com.cpd.repository.EstadoRepository;
import com.cpd.repository.LocalidadeRepository;
import com.cpd.repository.OrganizacaoRepository;

@Controller
public class MainController {

	@Autowired
	private OrganizacaoRepository organizacaoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private LocalidadeRepository localidadeRepository;

	@GetMapping(value = "/")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/home")
	public String home(Model model) {
		Organizacao org = organizacaoRepository.findAll(2).get(0);
		OrganizacaoModel orgMod = new OrganizacaoModel(org);
		Long ufId = org.getLocalidade().getEstado().getId();
		model.addAttribute("Organizacao", orgMod);
		model.addAttribute("Estados", estadoRepository.findAllByPais("Brasil"));
		model.addAttribute("Municipios", localidadeRepository.findByUfId(ufId));
		return "index";
	}

	@PostMapping(value = "/organizacao")
	@ResponseBody
	public void salvarOrganizacao(@ModelAttribute("Organizacao") OrganizacaoModel orgMod) {
		Organizacao org = organizacaoRepository.findAll(2).get(0);
		
		org.setOrganizacao(orgMod);
		org.setLocalidade(localidadeRepository.findById(orgMod.getLocalidadeId()).get());
		organizacaoRepository.save(org);
	}

	@GetMapping(value = "/administracao")
	public String administracao() {
		return "administracao";
	}

	@GetMapping(value = "/anoletivo")
	public String anoLetivo() {
		return "ano_letivo";
	}

	@GetMapping(value = "/acessoNegado")
	public String acessoNegado() {
		return "acessoNegado";
	}

}
