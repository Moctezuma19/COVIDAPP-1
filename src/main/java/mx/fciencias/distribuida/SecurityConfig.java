package mx.fciencias.distribuida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mx.fciencias.distribuida.service.MyAppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyAppUserDetailsService myAppUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/","/registro","/registrarse","/exito", "/inicio", "/css/**", "/fonts/**", "/images/**",
						"/js/**", "/favicon.ico","/iniciar-sesion")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/iniciar-sesion")
				.loginProcessingUrl("/iniciar-sesion").usernameParameter("correo").passwordParameter("password")
				.defaultSuccessUrl("/log/inicio").permitAll().and().logout()// logout configuration
				.logoutUrl("/salir").deleteCookies("JSESSIONID").invalidateHttpSession(true)
				.logoutSuccessUrl("/iniciar-sesion").permitAll();
	}

	// *
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);

	}
}
