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
        entityManagerFactoryRef = "odontoEntityManagerFactory",
        transactionManagerRef = "odontoTransactionManager",
        basePackages = "br.com.sf.apireaddata.dao.odonto.repository"
)
@EnableTransactionManagement
public class Db1Config {

    @Bean(name = "odontoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean odontoEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                             final @Qualifier("odonto-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.sf.apireaddata.dao.odonto.domain")
                .persistenceUnit("odontoDb")
                .properties(singletonMap("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect"))
                .properties(singletonMap("show-sql", "true"))
                .build();
    }

    @Bean(name = "odontoTransactionManager")
    public PlatformTransactionManager odontoTransactionManager(@Qualifier("odontoEntityManagerFactory")
                                                                       EntityManagerFactory odontoEntityManagerFactory) {
        return new JpaTransactionManager(odontoEntityManagerFactory);
    }
}