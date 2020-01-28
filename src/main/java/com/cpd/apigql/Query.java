package com.cpd.apigql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import org.neo4j.driver.v1.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.cpd.entity.nodes.NivelEscolar;
import com.cpd.entity.nodes.AnoLetivo;
import com.cpd.entity.nodes.Config;
import com.cpd.entity.nodes.Setor;
import com.cpd.entity.nodes.Estado;
import com.cpd.entity.nodes.Grupo;
import com.cpd.entity.nodes.Localidade;
import com.cpd.entity.nodes.MatriculaFuncional;
import com.cpd.entity.nodes.Usuario;
import com.cpd.repository.AnoLetivoRepository;
import com.cpd.repository.NivelEscolarRepository;
import com.cpd.repository.ConfigRepository;
import com.cpd.repository.CurrentDB;
import com.cpd.repository.GrupoRepository;
import com.cpd.repository.LocalidadeRepository;
import com.cpd.repository.SetorRepository;
import com.cpd.repository.EstadoRepository;
import com.cpd.repository.FuncionarioRepository;
import com.cpd.repository.UsuarioRepository;
import com.cpd.type.DiaSemana;
import com.cpd.type.Etnia;
import com.cpd.type.Turno;
import com.cpd.utils.Debug;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class Query {

	@Autowired
	private CurrentDB cypherRepository;

	@Autowired
	private NivelEscolarRepository anoRepository;

	@Autowired
	private AnoLetivoRepository anoLetivoRepository;

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

	@GraphQLQuery
	public List<Map<String, Object>> cypher(String query, String returns){
		List<Record> registros = cypherRepository.OpenResult(query);
        List<Map<String, Object>> queryMap = new ArrayList<Map<String, Object>>();
		
		try{
			IntStream.range(0, registros.size()).forEach(i -> queryMap.add(registros.get(i).get(returns).asMap()));
		}catch(Exception e){
			Debug.Print(e.getMessage());
		}
		return queryMap;
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

	// ANOS LETIVOS
	@GraphQLQuery
	public AnoLetivo obterAnoLetivo(int nome) {
		return anoLetivoRepository.findByNome(nome);
	}

	@GraphQLQuery
	public Iterable<AnoLetivo> obterAnosLetivos(int pag, int porpag, String sort) {
		Pageable sortedByNome = PageRequest.of(pag, porpag, Sort.by(sort));
		return anoLetivoRepository.findAll(sortedByNome);
	}

	// ANOS ESCOLARES

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

	@GraphQLQuery
	public long contarAnosLetivos() {
		return anoLetivoRepository.count();
	}

	// TIPOS

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