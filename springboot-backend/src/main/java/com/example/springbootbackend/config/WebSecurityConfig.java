package com.example.springbootbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/","/assets/**").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .anyRequest().authenticated()
            .and().formLogin().disable();

    //    http
//            .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/","/static/**", "/resources/**","/resources/public/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .and()
//            .httpBasic().disable()
//            .requiresChannel().anyRequest().requiresSecure();
  }

}