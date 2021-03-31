package com.vsvdev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vsvdev.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
jsr250Enabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final JWTAuthEntryPoint jwtEntryPoint;
	private final CustomUserDetailsService userService;

	@Autowired
	public SecurityConfig(JWTAuthEntryPoint jwtEntryPoint, CustomUserDetailsService userService) {
		this.jwtEntryPoint = jwtEntryPoint;
		this.userService = userService;
		}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests().antMatchers(SecurityConstants.SIGN_UP_URLS).permitAll()
		.anyRequest().authenticated();
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(encoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authManager() throws Exception{
		return super.authenticationManager();
	}
	@Bean
	public JWTAuthFilter authFilter() {
		return new JWTAuthFilter();
	}
	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
