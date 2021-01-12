package com.idrica.goaigua;

import com.idrica.goaigua.controller.GraphqlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author globalomnium
 *
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { CassandraDataAutoConfiguration.class })
@EnableAsync
public class Application extends SpringBootServletInitializer {

  /**
   * @param args Arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ServletRegistrationBean graphQLServlet() {
    return new ServletRegistrationBean(new GraphqlController(),"/graphql");
  }

}
