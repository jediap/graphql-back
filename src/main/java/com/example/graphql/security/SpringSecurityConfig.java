package com.example.graphql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BasicProvider basicProvider;

	@Autowired
	private TokenProvider tokenProvider;

	private static final String[] PUBLIC_GET_PATHS = {};

	private static final String[] PUBLIC_POST_PATHS = {};

	private static final String[] PUBLIC_PUT_PATHS = {};

	private static final String[] PUBLIC_DELETE_PATHS = {};

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowedOrigins(Arrays.asList(CorsConfiguration.ALL));
		corsConfiguration
				.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(new JWTFilter(tokenProvider, basicProvider), UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_GET_PATHS).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_POST_PATHS).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, PUBLIC_PUT_PATHS).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, PUBLIC_DELETE_PATHS).permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}
}