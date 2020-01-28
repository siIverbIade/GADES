package com.cpd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import com.cpd.service.UsuarioService;
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
        repo.setHeaderName("X-XSRF-TOKEN");
        return repo;
      }
	
	@Autowired
	private UsuarioService usuarioRepositoryImpl;
 
	/**
	 * REALIZA AS CONFIGURAÇÕES DE ACESSO
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			/*DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
		 	* EXEMPLO DA URL: http://localhost:8095/usuario/novoCadastro
		 	* VEJA QUE EM UM ITEM("hasRole('ADMIN')) NÃO ESTOU PASSANDO O PREFIXO ROLE_, ESSE PREFIXO NÃO É OBRIGATÓRIO 
		 	* QUANDO USAMOS o hasRole*/
			.antMatchers("/usuario/novoCadastro").access("hasRole('ROLE_ADMIN')")
			/*DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
			 * EXEMPLO DA URL: http://localhost:8095/usuario/consultar */
			.antMatchers("/usuario/consultar").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/sesmec").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SESMEC')")
			.antMatchers("/dme").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DME')")
			.antMatchers("/dne").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DNE')")
			.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
			//.antMatchers("/graphql").authenticated()
			 /*DETERMINA QUE PARA ACESSAR A PÁGINA INICIAL DA APLICAÇÃO PRECISA ESTAR AUTENTICADO*/
			.antMatchers("/home").authenticated()
			.anyRequest().authenticated()		
			.and()
				.formLogin()
				 /*INFORMANDO O CAMINHO DA PÁGINA DE LOGIN, E SE O LOGIN FOR EFETUADO COM SUCESSO
				  *O USUÁRIO DEVE SER REDIRECIONADO PARA /home(http://localhost:8095/home)*/
				.loginPage("/")
				//.loginProcessingUrl("/login/process")
				.defaultSuccessUrl("/home",true)
				.permitAll() /*AQUI ESTAMOS INFORMANDO QUE TODOS TEM ACESSO A PÁGINA DE LOGIN*/
			.and()
			     /*AQUI ESTAMOS INFORMANDO QUE QUANDO FOR REDIRECIONADO PARA  O LINK http://localhost:8095/logout
			      *O USUÁRIO DEVE TER SUA SESSÃO FINALIZADA E REDIRECIONADO PARA A PÁGINA DE LOGIN */
				.logout()
				.deleteCookies("remember-me")
				.logoutSuccessUrl("/")
				.logoutUrl("/logout") 
				.permitAll()
	            .and()
	            .rememberMe().key("uniqueAndSecret")
				.and()
				.addFilterAfter(new configurationCrsfFilter(), CsrfFilter.class)
				.csrf()
				.ignoringAntMatchers("/ano/**")
				.ignoringAntMatchers("/anoletivo/**")
				.ignoringAntMatchers("/graphql/**")
				.ignoringAntMatchers("/gui/**")
				.csrfTokenRepository(csrfTokenRepository());
 
		/*PÁGINA COM A MENSAGEM DE ACESSO NEGADO
		 *QUANDO O USUÁRIO NÃO TER UMA DETERMINADA PERMISSÃO DE ACESSO AO SISTEMA ELE VAI SER REDIRECIONADO
		 *PARA A URL ABAIXO */
		http.exceptionHandling().accessDeniedPage("/acessoNegado");
 
		/*AQUI ESTOU INFORMANDO QUE QUALQUER REQUEST TEM ACESSO AO DIRETÓRIO src/main/resources */
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
 
	}
 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        /*INFORMA A CRIPTOGRAFIA QUE DEVE SER USADA PARA A SENHA DO USUÁRIO*/				
		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
 
    }
}