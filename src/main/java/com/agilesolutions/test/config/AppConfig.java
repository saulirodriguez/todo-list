package com.agilesolutions.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.agilesolutions.test.entity"})
@EnableJpaRepositories(basePackages = {"com.agilesolutions.test.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class AppConfig {}