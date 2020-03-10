package com.cpd.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.cpd.entity.nodes.Grupo;
import com.cpd.entity.nodes.Permissao;
import com.cpd.entity.nodes.Usuario;
import com.cpd.model.UsuarioModel;
import com.cpd.model.UsuarioSecurityModel;
import com.cpd.repository.FuncionarioRepository;
import com.cpd.repository.GrupoRepository;
import com.cpd.repository.UsuarioRepository;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	/***
	 * CONSULTA UM USUÁRIO POR LOGIN
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException, DisabledException {

		if (login.equals(""))
			throw new BadCredentialsException("O login não pode estar em branco!");

		Usuario Usuario = usuarioRepository.findByLogin(login, 2);

		if (Usuario == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");

		if (!Usuario.isAtivo())
			throw new DisabledException("Usuário inativo!");

		UsuarioSecurityModel usm = new UsuarioSecurityModel(Usuario.getLogin(), Usuario.getSenha(), Usuario.isAtivo(),
					this.buscarPermissoesUsuario(Usuario));
			
		return usm;
	}

	/***
	 * BUSCA AS PERMISSÕES DO USUÁRIO
	 * 
	 * @param Usuario
	 * @return
	 */
	public List<GrantedAuthority> buscarPermissoesUsuario(Usuario Usuario) {

		List<Grupo> grupos = Usuario.getGrupo();
		return this.buscarPermissoesDosGrupos(grupos);
	}

	/***
	 * BUSCA AS PERMISSÕES DO GRUPO
	 */

	public List<GrantedAuthority> buscarPermissoesDosGrupos(List<Grupo> grupos) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		for (Grupo grupo : grupos) {

			List<Permissao> lista = grupo.getPermissao();

			lista.forEach(permissao -> {
				auths.add(new SimpleGrantedAuthority(permissao.getPermissao()));
				System.out.println(permissao.getPermissao());
			});
		}

		return auths;
	}

	/***
	 * SALVA UM NOVO REGISTRO DE USUÁRIO
	 * 
	 * @param usuarioModel
	 */
	public void salvarUsuario(UsuarioModel usuarioModel) {

		Usuario Usuario = new Usuario();

		/* SETA O USUÁRIO COMO ATIVO NO SISTEMA */
		Usuario.setAtivo(true);

		/* LOGIN DO USUÁRIO */
		Usuario.setLogin(usuarioModel.getLogin());

		/* FUNCIONARIO A SER ASSOCIADO À ESTE USUÁRIO */
		Usuario.setMatricula(funcionarioRepository.findById(usuarioModel.getFuncionarioId()).get());

		/* CRIPTOGRAMA E INFORMA A SENHA */
		Usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		/* PEGANDO A LISTA DE GRUPOS SELECIONADOS */
		Grupo Grupo = null;
		List<Grupo> grupos = new ArrayList<Grupo>();
		for (Long idGrupo : usuarioModel.getGrupos()) {

			if (idGrupo != null) {

				/* CONSULTA GRUPO POR CÓDIGO */
				Grupo = grupoRepository.findById(idGrupo).get();

				/* ADICIONA O GRUPO NA LISTA */
				grupos.add(Grupo);
			}
		}

		/* SETA A LISTA DE GRUPO DO USUÁRIO */
		Usuario.setGrupo(grupos);

		/* SALVANDO O REGISTRO */
		usuarioRepository.save(Usuario, 2);
	}

	/***
	 * CONSULTA OS USUÁRIOS CADASTRADOS
	 * 
	 * @return
	 */
	public List<UsuarioModel> consultarUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();

		Iterable<Usuario> usuariosEntity = usuarioRepository.findAll(2);

		usuariosEntity.forEach(Usuario -> {
			// Debug.Print(Usuario);
			usuariosModel.add(new UsuarioModel(Usuario.getId(), Usuario.getMatricula().getId(),
					Usuario.getMatricula().getFuncionario().getNome(), Usuario.getLogin(), null, Usuario.isAtivo(),
					null));
		});

		return usuariosModel;
	}

	/**
	 * DELETA UM USUÁRIO PELO CÓDIGO
	 */
	public void excluir(Long idUsuario) {

		this.usuarioRepository.deleteById(idUsuario);
	}

	/***
	 * CONSULTA UM USUÁRIO PELO SEU CÓDIGO
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public UsuarioModel consultarUsuario(Long idUsuario) {

		Usuario Usuario = this.usuarioRepository.findById(idUsuario, 2).get();

		List<Long> grupos = new ArrayList<Long>();

		Usuario.getGrupo().forEach(grupo -> {

			grupos.add(grupo.getId());

		});

		return new UsuarioModel(Usuario.getId(), Usuario.getMatricula().getId(),
				Usuario.getMatricula().getFuncionario().getNome(), Usuario.getLogin(), null, Usuario.isAtivo(), grupos);

	}

	/**
	 * ALTERA AS INFORMAÇÕES DO USUÁRIO
	 */

	public void alterarUsuario(UsuarioModel usuarioModel) {
		Usuario usuario = usuarioRepository.findById(usuarioModel.getId(), 2).get();
		/* USUÁRIO ATIVO OU INATIVO */
		usuario.setAtivo(usuarioModel.isAtivo());

		/* LOGIN DO USUÁRIO */
		usuario.setLogin(usuarioModel.getLogin());

		/* NOME DO USUÁRIO A SER SALVO */
		usuario.setMatricula(funcionarioRepository.findById(usuarioModel.getFuncionarioId()).get());

		/* CRIPTOGRAMA E INFORMA A SENHA */
		if (!StringUtils.isEmpty(usuarioModel.getSenha()))
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		/* PEGANDO A LISTA DE GRUPOS SELECIONADOS */
		Grupo Grupo = null;
		List<Grupo> grupos = new ArrayList<Grupo>();
		for (Long idGrupo : usuarioModel.getGrupos()) {

			if (idGrupo != null) {
				/* CONSULTA GRUPO POR CÓDIGO */
				Grupo = grupoRepository.findById(idGrupo).get();
				/* ADICIONA O GRUPO NA LISTA */
				grupos.add(Grupo);

			}
		}

		/* SETA A LISTA DE GRUPO DO USUÁRIO */
		usuario.setGrupo(grupos);
		/* SALVANDO ALTERAÇÃO DO REGISTRO */
		usuarioRepository.save(usuario, 2);

	}

}
