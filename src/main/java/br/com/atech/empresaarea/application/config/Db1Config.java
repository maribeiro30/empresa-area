package br.com.atech.empresaarea.application.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager",
        basePackages = "br.com.sf.apireaddata.dao.db1.repository"
)
@EnableTransactionManagement
public class Db1Config {

    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                             final @Qualifier("db1-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.sf.apireaddata.dao.db1.domain")
                .persistenceUnit("db1Db")
                .properties(singletonMap("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect"))
                .properties(singletonMap("show-sql", "true"))
                .build();
    }

    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManagerFactory")
                                                                       EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }
}