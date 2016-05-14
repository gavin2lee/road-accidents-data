package com.gl.roadaccidents.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Date;

/**
 * Created by gavin on 16-5-14.
 */
@Configuration
@PropertySource("classpath:config/data-access.properties")
public class DataSourceConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    Environment env;

    @Bean(name="dataSource")
    public DataSource getDataSource(){
        String url = env.getProperty("jdbc.url");
        String username = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");
        String driverClassName = env.getProperty("jdbc.driverClassName");

        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        log.info("A datasource has been created at " + (new Date().toString()));

        return ds;
    }

    @Bean(name="dataSource")
    @Profile({"prod"})
    public DataSource getDataSourceForProduction(){
        return null;
    }
}
