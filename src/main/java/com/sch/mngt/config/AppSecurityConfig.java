package com.sch.mngt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sch.mngt.filter.ApplicationSecurityFilter;
import com.sch.mngt.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private CustomUserDetailService  customUserDetailService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/login**").permitAll()
		.antMatchers("/logout**").permitAll()
		.antMatchers("/register**").permitAll();
		http.addFilterBefore(new ApplicationSecurityFilter(customUserDetailService), UsernamePasswordAuthenticationFilter.class);

	}

}
