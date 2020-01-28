package com.cpd.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpd.model.GrupoModel;
import com.cpd.model.UsuarioModel;
import com.cpd.repository.FuncionarioRepository;
import com.cpd.service.GrupoService;
import com.cpd.service.UsuarioService;

/**
 * 
 * 
 *OBJETO RESPONSÁVEL POR REALIZAR AS ROTINAS DE USUÁRIO.
 * 
 */
@Controller
@RequestMapping("/usuario") 
public class UsuarioController {
	
	/**INJETANDO O OBJETO GrupoService*/
	@Autowired
	private GrupoService grupoService;
	
	/**INJETANDO O OBJETO UsuarioService*/
	@Autowired 
	private UsuarioService usuarioService;
	
	/**INJETANDO O OBJETO FuncionarioRepository*/
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/***
	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVO USUÁRIO NO SISTEMA
	 * @param model
	 * @return
	 */
	@GetMapping(value="/novoCadastro")
	public ModelAndView novoCadastro(Model model) {
				
		/*LISTA DE Funcionários QUE VAMOS MOSTRAR NO SELETOR*/
		model.addAttribute("funcionarios", funcionarioRepository.findAll(2));
		
		/*LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA*/
		model.addAttribute("grupos", grupoService.consultarGrupos());
		
		/*OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS*/
		model.addAttribute("usuarioModel", new UsuarioModel());
		
	    return new ModelAndView("cadastrar_usuario");
	}
	
	
	/***
	 * SALVA UM NOVO USUÁRIO NO SISTEMA
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping(value="/salvarUsuario")
	public ModelAndView salvarUsuario(@ModelAttribute 
								@Valid UsuarioModel usuarioModel, 
								final BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes){
        
		/*VERIFICA SE TEM ALGUM ERRO (@NotEmpty),
		 *SE TIVER ALGUM ERRO DEVEMOS RETORNAR PARA A MESMA PÁGINA PARA O USUÁRIO CORRIGIR*/
		if(result.hasErrors()){
			
			List<GrupoModel> gruposModel = grupoService.consultarGrupos();			
			/*POSICIONANDO OS CKECKBOX SELECIONADOS
			 * SE O SISTEMA ENCONTROU ALGUM ERRO DE VALIDAÇÃO DEVEMOS 
			 * BUSCAR OS GRUPOS E MARCAR COMO SELECIONADO NOVAMENTE 
			 * PARA MOSTRAR NÁ PÁGINAS DA FORMA QUE O USUÁRIO ENVIO A REQUEST*/
			gruposModel.forEach(grupo ->{
				if(usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0){
					
					usuarioModel.getGrupos().forEach(grupoSelecionado->{
						
						/*DEVEMOS MOSTRAR NA PÁGINA OS GRUPOS COM O CHECKBOX SELECIONADO*/
						if(grupoSelecionado!= null){
							if(grupo.getId().equals(grupoSelecionado))
								grupo.setChecked(true);
						}					
					});				
				}
			});
			
			/*LISTA DE Funcionários QUE VAMOS MOSTRAR NO SELETOR*/
			model.addAttribute("funcionarios", funcionarioRepository.findAll(2));
			
			/*ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA PÁGINA*/
			model.addAttribute("grupos", gruposModel);
			
			/*ADICIONA OS DADOS DO USUÁRIO PARA COLOCAR NO FORMULÁRIO*/
			model.addAttribute("usuarioModel", usuarioModel);
			
			/*RETORNA A VIEW*/
			return new ModelAndView("cadastrar_usuario");	
		}else{
			
			/*SALVANDO UM NOVO REGISTRO*/
			usuarioService.salvarUsuario(usuarioModel);
			
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/novoCadastro");
		
		/*PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR 
		 * O REDIRECIONAMENTO COM A MENSAGEM DE SUCESSO*/
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		
		/*REDIRECIONANDO PARA UM NOVO CADASTRO*/
		
		return modelAndView;

	}
	
	/***
	 * CONSULTA TODOS USUÁRIOS CADASTRADOS NO SISTEMA
	 * @param model
	 * @return
	 */
	@GetMapping(value="/consultar")
	public ModelAndView consultar(Model model) {
		
		/*CONSULTA USUÁRIOS CADASTRADOS*/
		model.addAttribute("usuariosModel", usuarioService.consultarUsuarios());
		
		/*RETORNA A VIEW*/
	    return new ModelAndView("consulta_usuario");
	}
	
	/***
	 * EXCLUI UM REGISTRO DO SISTEMA PELO CÓDIGO
	 * @param codigoUsuario
	 * @return
	 */
	@PostMapping(value="/excluir")
	public ModelAndView excluir(@RequestParam("idUsuario") Long idUsuario){
	
		ModelAndView modelAndView = new ModelAndView("consulta_usuario");
		
		/*EXCLUINDO O REGISTRO*/
		this.usuarioService.excluir(idUsuario);
		
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
	

	/***
	 * CONSULTA UM USUÁRIO PELO CÓDIGO PARA REALIZAR ALTERAÇÕES NAS INFORAMÇÕES CADASTRADAS.
	 * @param codigoUsuario
	 * @param model
	 * @return
	 */
	
	@GetMapping(value="/editarCadastro")
	public ModelAndView editarCadastro(@RequestParam("idUsuario") Long idUsuario, Model model) {

		/*CONSULTA OS GRUPOS CADASTRADOS*/
		List<GrupoModel> gruposModel = grupoService.consultarGrupos();			
		
		/*CONSULTA O USUÁRIO PELO CÓDIGO*/
		UsuarioModel usuarioModel = this.usuarioService.consultarUsuario(idUsuario);

		/*DEIXA SELECIONADO OS GRUPOS CADASTRADOS PARA O USUÁRIO*/
		gruposModel.forEach(grupo ->{

			usuarioModel.getGrupos().forEach(grupoCadastrado->{

				if(grupoCadastrado!= null){
					if(grupo.getId().equals(grupoCadastrado))
						grupo.setChecked(true);
				}					
			});				
			
		});
		
		/*LISTA DE Funcionários QUE VAMOS MOSTRAR NO SELETOR*/
		model.addAttribute("funcionarios", funcionarioRepository.findAll(2));
		
		/*ADICIONANDO GRUPOS PARA MOSTRAR NA PÁGINA(VIEW)*/
		model.addAttribute("grupos", gruposModel);
		
		/*ADICIONANDO INFORMAÇÕES DO USUÁRIO PARA MOSTRAR NA PÁGINA(VIEW)*/
		model.addAttribute("usuarioModel", usuarioModel);
		
		/*CHAMA A VIEW /src/main/resources/templates/editarCadastro.html*/
	    return new ModelAndView("editar_usuario");
	}
	
	/***
	 * SALVA AS ALTERAÇÕES REALIZADAS NO CADASTRO DO USUÁRIO
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@PostMapping(value="/salvarAlteracao")
	public ModelAndView salvarAlteracao(@ModelAttribute 
								@Valid 
								UsuarioModel usuarioModel, 
								final BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes){
		
		boolean isErroNullCampos = false;
		
		/*AQUI ESTAMOS VERIFICANDO SE TEM ALGUM CAMPO QUE NÃO ESTÁ PREENCHIDO,
		 * MENOS O CAMPO DA SENHA, POIS SE O USUÁRIO NÃO INFORMAR VAMOS MANTER A
		 * SENHA JÁ CADASTRADA*/
		for (FieldError fieldError : result.getFieldErrors()) {
			if(!fieldError.getField().equals("senha")){
				isErroNullCampos = true;	
			}	
		}
		
		/*SE ENCONTROU ERRO DEVEMOS RETORNAR PARA A VIEW PARA QUE O 
		 * USUÁRIO TERMINE DE INFORMAR OS DADOS*/
		if(isErroNullCampos){
			
			List<GrupoModel> gruposModel =grupoService.consultarGrupos();			
			
			gruposModel.forEach(grupo ->{
				
				if(usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() >0){
					
					/*DEIXA CHECADO OS GRUPOS QUE O USUÁRIO SELECIONOU*/
					usuarioModel.getGrupos().forEach(grupoSelecionado->{
						
						if(grupoSelecionado!= null){
							if(grupo.getId().equals(grupoSelecionado))
								grupo.setChecked(true);
						}					
					});				
				}
				
			});
			
			/*LISTA DE Funcionários QUE VAMOS MOSTRAR NO SELETOR*/
			model.addAttribute("funcionarios", funcionarioRepository.findAll(2));
			
			/*ADICIONANDO GRUPOS PARA MOSTRAR NA PÁGINA(VIEW)*/
			model.addAttribute("grupos", gruposModel);
			
			/*ADICIONANDO O OBJETO usuarioModel PARA MOSTRAR NA PÁGINA(VIEW) AS INFORMAÇÕES DO USUÁRIO*/
			model.addAttribute("usuarioModel", usuarioModel);
			
			/*RETORNANDO A VIEW*/
			return new ModelAndView("editar_usuario");	
		}
		else{
			/*SALVANDO AS INFORMAÇÕES ALTERADAS DO USUÁRIO*/
			usuarioService.alterarUsuario(usuarioModel);
		}
		
		/*APÓS SALVAR VAMOS REDIRICIONAR O USUÁRIO PARA A PÁGINA DE CONSULTA*/
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");
				
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
}
