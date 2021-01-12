package com.idrica.goaigua.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idrica.goaigua.config.DatabaseMigrator;

/**
 * Java configuration for JPA
 */
@Configuration
public class BeansJpaConfig {

  @Autowired
  private DataSource appDataSource;

  @Value("${database.migration.auto}")
  private Boolean enabled;

  @Value("${database.migration.testdata}")
  private Boolean testdata;

  @Value("${database.migration.clean}")
  private Boolean clean;

  /**
   * @return migrator
   */
  @Bean
  public DatabaseMigrator getFlyway() {

    DatabaseMigrator migrator = new DatabaseMigrator();
    migrator.setClean(this.clean);
    migrator.setDataSource(this.appDataSource);
    migrator.setEnabled(this.enabled);
    migrator.setTestdata(this.testdata);
    return migrator;

  }

  /**
   * Migrate
   */
  @PostConstruct
  public void migrate() {

    getFlyway().migrate();
  }

}