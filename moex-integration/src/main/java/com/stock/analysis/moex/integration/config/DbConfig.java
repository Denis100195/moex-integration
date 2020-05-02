package com.stock.analysis.moex.integration.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DbConfig {
    @Bean(name = "hikariDS")
    @ConfigurationProperties("postgres.hikari")
    public HikariDataSource getHikariDataSource(){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
    @Bean(name = "trManager")
    @Autowired
    public DataSourceTransactionManager transactionManager(@Qualifier("hikariDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }




}
