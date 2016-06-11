package com.hackaton.psd2.filter;

import com.hackaton.psd2.security.TokenMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenFilter implements Filter {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final TokenMap tokenMap;

  public TokenFilter(TokenMap tokenMap) {
    this.tokenMap = tokenMap;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    String user = "";
    if (req.getUserPrincipal() != null) {
      user = req.getUserPrincipal().getName();
    }
    try {
      if (!user.isEmpty() && tokenMap.getUserToken(user) == null) {
        tokenMap.setUserToken(user, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.vaXgQ27FGRfXsKrBbnHKskZDIwmeG8tV_2qeExRQxZQ");
      }
    } catch (Exception e) {
      log.error(String.format("No se pudo recuperar el token para el usuario %s", user), e);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {}
}
