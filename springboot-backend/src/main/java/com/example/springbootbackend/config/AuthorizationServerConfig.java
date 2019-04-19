package com.example.springbootbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
  public static Logger LOG = LoggerFactory.getLogger(AuthorizationServerConfig.class);

  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
      .inMemory()
      .withClient("clientId")
      // Como los clientes web no mandan secret (JS), el vacio es el unico valor que matchea
      .secret(passwordEncoder().encode(""))
      .scopes("any")
      .authorizedGrantTypes("authorization_code","client_credentials","password")
      .redirectUris("/");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    LOG.info("Elegido para auth server: {}", authenticationManager);
    endpoints.authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    // Esto permite que los parametros para obtener un access token vengan como query params o en el body
    security
            .allowFormAuthenticationForClients();
  }

}