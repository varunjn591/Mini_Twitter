package com.challenge.application.security;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/h2-console/*").permitAll().anyRequest().fullyAuthenticated().and().httpBasic();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final Properties users = new Properties();
		users.put("batman", "test,USER,enabled");
		users.put("superman", "test,USER,enabled");
		users.put("catwoman", "test,USER,enabled");
		users.put("daredevil", "test,USER,enabled");
		users.put("alfred", "test,USER,enabled");
		users.put("dococ", "test,USER,enabled");
		users.put("zod", "test,USER,enabled");
		users.put("ironman", "test,USER,enabled");
		users.put("profx", "test,USER,enabled");
		return new InMemoryUserDetailsManager(users);
	}
}