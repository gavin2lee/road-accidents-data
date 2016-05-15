package com.gl.roadaccidents.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Date;

/**
 * Created by gavin on 16-5-13.
 */
@Configuration
//@EnableLoadTimeWeaving
//@Import({DataSourceConfiguration.class})
@ImportResource(locations={"classpath:config/datasource.xml"})
@EnableTransactionManagement
@PropertySource("classpath:config/data-access.properties")
@EnableJpaRepositories(basePackages={"com.gl.roadaccidents.repository","com.gl.roadaccidents.model"})
@ComponentScan(basePackages={"com.gl.roadaccidents.model"})
public class RepositoryConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RepositoryConfiguration.class);
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    Environment env;

    @Bean(name="jpaVendorAdapter")
    public JpaVendorAdapter getJpaVendorAdapter(){
        String database = env.getProperty("jpa.database");
        String showSql = env.getProperty("jpa.showSql");
        String generateDdl = env.getProperty("jdbc.generateddl");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(Boolean.valueOf(showSql));
        adapter.setGenerateDdl(Boolean.valueOf(generateDdl));
        adapter.setDatabase(Database.valueOf(database));

        log.info(HibernateJpaVendorAdapter.class.getSimpleName() + " has been created at " + new Date().toString());

        return adapter;
    }

    @Bean
    public JpaDialect getJpaDialect(){
        return new HibernateJpaDialect();
    }

    @Bean(name="entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(getJpaVendorAdapter());
        emfb.setJpaDialect(getJpaDialect());
        //emfb.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        //emfb.setPersistenceUnitName("roadAccidents");
        emfb.setPackagesToScan("com.gl.roadaccidents.model");

        emfb.afterPropertiesSet();

        return emfb.getNativeEntityManagerFactory();
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager getTransactionManager(){
        JpaTransactionManager tm = new JpaTransactionManager();
        EntityManagerFactory emf = getEntityManagerFactory();
        if(emf == null){
            throw new RuntimeException("Failed to initialize the application context.");
        }
        tm.setEntityManagerFactory(emf);

        return tm;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor  getPersistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
