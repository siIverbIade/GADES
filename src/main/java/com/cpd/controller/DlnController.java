package com.cpd.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cpd.entity.nodes.Setor;
import com.cpd.entity.nodes.MatriculaFuncional;
import com.cpd.entity.nodes.RotuloCalendario;
import com.cpd.model.CalendarioEscolarModel;
import com.cpd.model.EscolaModel;
import com.cpd.repository.ConfigRepository;
import com.cpd.repository.SetorRepository;
import com.cpd.service.CalendarioEscolarService;
import com.cpd.repository.EstadoRepository;
import com.cpd.repository.FuncaoRepository;
import com.cpd.repository.FuncionarioRepository;
import com.cpd.repository.LocalidadeRepository;
import com.cpd.repository.LotacaoRepository;
import com.cpd.repository.OrganizacaoRepository;
import com.cpd.repository.RotuloRepository;

@Controller
@RequestMapping("/dln")
public class DlnController {

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private ConfigRepository configRepository;

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Autowired
	private OrganizacaoRepository organizacaoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private LotacaoRepository lotacaoRepository;

	@Autowired
	private CalendarioEscolarService calendarioEscolarService;

	@Autowired
	private RotuloRepository rotuloRepository;

	@GetMapping("")
	public String sesmec() {
		return "dln";
	}

	@GetMapping("/escola")
	public String carregarEscola(Model model) {
		Long inep = configRepository.get().getEscolaInep();
		Long fId1 = funcaoRepository.findByNome("DIRETOR ESCOLAR").getId();
		Long fId2 = funcaoRepository.findByNome("SECRETÁRIO ESCOLAR").getId();

		Setor esc = setorRepository.findByInep(inep, 2);
		EscolaModel escMod = new EscolaModel(esc);

		List<Setor> escolas = setorRepository.findAllEscolas();
		List<MatriculaFuncional> diretoresLotados = funcionarioRepository.findAllByLotacao(esc.getId(), fId1);
		List<MatriculaFuncional> secretariosLotados = funcionarioRepository.findAllByLotacao(esc.getId(), fId2);

		if (diretoresLotados.size() != 0) {
			escMod.setDiretorId(diretoresLotados.get(0).getId());
		}

		if (secretariosLotados.size() != 0) {
			escMod.setSecretarioId(secretariosLotados.get(0).getId());
		}
		Long ufId = esc.getLocalidade().getEstado().getId();

		model.addAttribute("Estados", estadoRepository.findAllByPais("Brasil"));
		model.addAttribute("Municipios", localidadeRepository.findByUfId(ufId));
		model.addAttribute("Organizacoes", organizacaoRepository.findAll());
		model.addAttribute("Diretores", funcionarioRepository.findAllByClasse("Pedagógico"));
		model.addAttribute("Secretarios", funcionarioRepository.findAll());
		model.addAttribute("Escola", escMod);
		model.addAttribute("Escolas", escolas);
		return "escola";
	}

	@PostMapping("/escola")
	@ResponseBody
	public String salvarEscola(@ModelAttribute("Escola") EscolaModel escolaModel) {
		Setor esc = setorRepository.findByInep(escolaModel.getInep(), 2);

		esc.setEscola(escolaModel);
		esc.setLocalidade(localidadeRepository.findById(escolaModel.getLocalidadeId()).get());

		Long fId1 = funcaoRepository.findByNome("DIRETOR ESCOLAR").getId();
		Long fId2 = funcaoRepository.findByNome("SECRETÁRIO ESCOLAR").getId();

		if (escolaModel.getDiretorId() == null) {
			lotacaoRepository.removeLotacao(esc.getId(), fId1);
		} else {
			lotacaoRepository.setLotacao(escolaModel.getDiretorId(), esc.getId(), fId1);
		}

		if (escolaModel.getSecretarioId() == null) {
			lotacaoRepository.removeLotacao(esc.getId(), fId2);
		} else {
			lotacaoRepository.setLotacao(escolaModel.getSecretarioId(), esc.getId(), fId2);
		}

		configRepository.get().setEscolaInep(escolaModel.getInep());
		setorRepository.save(esc);
		return "OK";
	}

	@GetMapping("/calendarioescolar")
	public String loadCalendarioEscolar() {
		return "calendario_escolar";
	}

	@GetMapping("/calendarioescolarmes")
	public String loadCalendarioEscolarMes(@RequestParam(required = true) int anoLetivo,
			@RequestParam(required = true) int mes, Model model) {
		CalendarioEscolarModel cem = new CalendarioEscolarModel(anoLetivo, mes,
				calendarioEscolarService.getListaRotulo(mes, anoLetivo));
		List<RotuloCalendario> rcm =rotuloRepository.findByGlobal(false);
		model.addAttribute("Calendario", cem);
		model.addAttribute("Rotulos", rcm);
		return "calendario_mes";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
