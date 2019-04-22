package com.example.springbootbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * Esta clase permite exponer como beans los objetos que controlan la autenticacion.<br>
 *   Si no definieramos esta clase, no serían accesibles
 */
@Configuration
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


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}