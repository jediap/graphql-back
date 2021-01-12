package com.idrica.goaigua.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author globalomnium
 *
 */
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
"com.globalomnium.goaigua.microservicetemplate.repository" })
@EntityScan(basePackages = "com.globalomnium.goaigua.microservicetemplate.domain.entity")
@Configuration
public class Config {

}