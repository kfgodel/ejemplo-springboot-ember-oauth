package com.example.springbootbackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${custom.api.path}")
@RestController
public class HelloController {
  public static Logger LOG = LoggerFactory.getLogger(HelloController.class);

  @ResponseBody
  @GetMapping("/hello")
  public Authentication getMessage(Authentication authentication) {
    LOG.info("Holder principal: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return authentication;
  }

}