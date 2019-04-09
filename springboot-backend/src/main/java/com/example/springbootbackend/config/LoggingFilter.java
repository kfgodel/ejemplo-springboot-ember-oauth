package com.example.springbootbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Esta clase permite loguear los requests y sus respuestas para identificar errores
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class LoggingFilter implements Filter {
  public static Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

  public static volatile long requestNumber = 0;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    long requestId = requestNumber++;
    logRequestData(requestId, (HttpServletRequest) request);
    chain.doFilter(request, response);
    logResponseData(requestId, (HttpServletResponse) response);
  }


  private void logRequestData(long requestId, HttpServletRequest serverHttpRequest) {
    LOG.info("<--- {}:[{} \"{}\"] FROM [{}]",
        requestId,
        serverHttpRequest.getMethod(), serverHttpRequest.getRequestURI(),
        serverHttpRequest.getRemoteAddr()
    );
  }

  private void logResponseData(long requestId, HttpServletResponse response) {
    int statusCode = response.getStatus();
    LOG.info("---> {}: {} ", requestId, statusCode);
  }

}