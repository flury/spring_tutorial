package com.kamarullah.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kamarullah.spring.service.UserDetailsServiceImp;

/**
 * 
 * Class : LoginSecurityConfig
 * Create Date : 22/Sep/2019
 * 
 * @author Wahid Kamarullah
 *
 * Copyright(C) 2019, KamarullahLabs (Indonesia) Co., Ltd. All rights reserved.
 */
@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImp();
	};
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/homePage").access("hasRole('ROLE_USER')")
			.and()
				.formLogin().loginPage("/loginPage")
			.defaultSuccessUrl("/homePage")
			.failureUrl("/loginPage?error")
				.usernameParameter("username")
				.passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/loginPage?logout"); 
	}

}
