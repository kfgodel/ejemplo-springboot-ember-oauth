package com.example.springbootbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  protected org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
      User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build());
  }

}