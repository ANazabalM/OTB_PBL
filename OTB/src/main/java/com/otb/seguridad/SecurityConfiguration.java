package com.otb.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.otb.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	

	/*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/registro**",
				"/js/**",
				"/css/**",
				"/img/**",
				"/",
				"/login",
				"/articulo/view/**",
				"/categoria/view/**",
				"/usuario/view/**"
				).permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/?logout")
		.permitAll();
	}

	/*public static String getAuthenticationEmail()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return auth.getName();
	} */
	
}






