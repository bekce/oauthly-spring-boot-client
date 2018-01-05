package com.sebworks.oauthly.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class Application extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		OAuth2Authentication pra = (OAuth2Authentication) principal;
		OAuth2AuthenticationDetails prd = (OAuth2AuthenticationDetails) pra.getDetails();
		LinkedHashMap details = (LinkedHashMap) pra.getUserAuthentication().getDetails();
		String id = (String) details.get("id");
		String name = (String) details.get("name");
		String email = (String) details.get("email");
		logger.info(String.format("id=%s, name=%s, email=%s", id, name, email));
		return principal;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/login**", "/webjars/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
