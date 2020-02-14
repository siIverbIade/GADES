package com.cpd.apigql;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.cpd.entity.nodes.*;
import com.cpd.model.RotuloCalendarioModel;
import com.cpd.repository.*;
import com.cpd.type.*;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class Query {

	@Autowired
	private CurrentDB cypherRepository;

	@Autowired
	private NivelEscolarRepository anoRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConfigRepository configRepository;

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private RotuloRepository rotuloRepository;

	@GraphQLQuery
	public List<RotuloCalendario> rotulosGlobais(){
		return rotuloRepository.findByGlobal(true);
	}

	@GraphQLQuery
	public int totalRotulos(String nome, int anoLetivo) {
		return rotuloRepository.findByNome(nome).getTotalDates(anoLetivo);
	}

	@GraphQLQuery
	public String datasRotulos(String nome, int anoLetivo, int ord) {
		return rotuloRepository.findByNome(nome).getDates(anoLetivo, ord);
	}

	@GraphQLQuery
	public List<RotuloCalendarioModel> rotulosAnoLetivo(int anoLetivo) {
		return rotuloRepository.rotulosAnoLetivo(anoLetivo);
	}

	@GraphQLQuery
	public List<Map<String, Object>> cypher(String query, String returns) {
		return cypherRepository.loadCypher(query, returns);
	}

	// LOCALIDADE
	@GraphQLQuery
	public List<Localidade> filtrarMunicipioByUfSigla(String uf) {
		return localidadeRepository.findByUf(uf);
	}

	@GraphQLQuery
	public List<Localidade> filtrarMunicipioByUfId(Long ufid) {
		return localidadeRepository.findByUfId(ufid);
	}

	@GraphQLQuery
	public List<Localidade> filtrarMunicipioByIbge(int cod) {
		return localidadeRepository.findByCod(cod);
	}

	@GraphQLQuery
	public Iterable<Estado> obterListaUFBR() {
		return estadoRepository.findAllByPais("Brasil");
	}

	// FUNCIONARIOS
	@GraphQLQuery
	public MatriculaFuncional obterFuncionario(String nome) {
		return funcionarioRepository.findFuncionarioByNome(nome);
	}

	@GraphQLQuery
	public Iterable<MatriculaFuncional> obterFuncionarios() {
		return funcionarioRepository.findAll(2);
	}

	@GraphQLQuery
	public Iterable<MatriculaFuncional> obterFuncionarios(String nomeSecretaria) {
		return funcionarioRepository.findAllFuncionariosBySecretaria(nomeSecretaria);
	}

	@GraphQLQuery
	public Iterable<MatriculaFuncional> obterPorClasse(String classe) {
		return funcionarioRepository.findAllByClasse(classe);
	}

	// NIVEL ESCOLAR

	@GraphQLQuery
	public Iterable<NivelEscolar> obterAnos() {
		return anoRepository.findAll(2);
	}

	@GraphQLQuery
	public Optional<NivelEscolar> obterAno(long id) {
		return anoRepository.findById(id);
	}

	@GraphQLQuery
	public long contarAnos() {
		return anoRepository.count();
	}

	// TIPOS

	@GraphQLQuery
	public List<String> meses(Boolean completo) {
		List<String> l = new ArrayList<String>();
		for (MesesAno mes : MesesAno.values()) {
			if (completo) {
				l.add(mes.FormatoCompleto());
			} else {
				l.add(mes.FormatoAbreviado());
			}
		}
		return l;
	}

	@GraphQLQuery
	public List<DiaSemana> semanas() {
		return Arrays.asList(DiaSemana.values());
	}

	@GraphQLQuery
	public List<Turno> turnos() {
		return Arrays.asList(Turno.values());
	}

	@GraphQLQuery
	public List<Etnia> etnia() {
		return Arrays.asList(Etnia.values());
	}

	// USER

	@GraphQLQuery
	public Iterable<Usuario> obterUsuarios() {
		return usuarioRepository.findAll(2);
	}

	@GraphQLQuery
	public String currentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@GraphQLQuery
	public Iterable<Grupo> obterGrupos() {
		return grupoRepository.findAll();
	}

	@GraphQLQuery
	public Usuario obterUsuario(String login) {
		return usuarioRepository.findByLogin(login, 2);
	}

	// SYSTEM CONFIG

	@GraphQLQuery
	public String setConfig(Config conf) {
		if (conf.getEscolaInep() == null || setorRepository.findByInep(conf.getEscolaInep(), 0) == null) {
			return "Erro Inep";
		} else {
			Config c = configRepository.get();
			c.setConfig(conf);
			configRepository.save(c);
			return "OK";
		}
	};

	@GraphQLQuery
	public Boolean firstRun(Boolean set) {
		Config cfg = configRepository.get();
		Boolean fr = cfg.getFirstRun();
		if (set != null) {
			cfg.setFirstRun(set);
			configRepository.save(cfg);
		}
		return fr;
	}

	// ESCOLAS

	@GraphQLQuery
	public Setor inst(long inep) {
		Setor escola = setorRepository.findByInep(inep, 2);
		return escola;
	}
}