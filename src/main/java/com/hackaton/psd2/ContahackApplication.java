package com.hackaton.psd2;

import javax.servlet.Filter;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hackaton.psd2.filter.TokenFilter;
import com.hackaton.psd2.security.TokenMap;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hackaton.psd2"})
@EnableSwagger2
@EnableJpaRepositories(basePackages={"com.hackaton.psd2.dao.repository"})
@EntityScan("com.hackaton.psd2.dao.model")
public class ContahackApplication {

  @Autowired
  private TokenMap tokenMap;

  public static void main(String[] args) {
    SpringApplication.run(ContahackApplication.class, args);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build();
  }

  @Bean
  public FilterRegistrationBean tokenFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(tokenFilter());
    registration.addUrlPatterns("/index.html");
    registration.setName("tokenFilter");
    registration.setOrder(1);
    return registration;
  }

  @Bean(name = "tokenFilter")
  public Filter tokenFilter() {
    return new TokenFilter(tokenMap);
  }
  
  @Bean
  ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
      registrationBean.addUrlMappings("/console/*");
      return registrationBean;
  }

}
