package br.com.atech.empresaarea.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackages = "br.com.sf.apireaddata.dao.db2.repository"
)
@EnableTransactionManagement
public class Db2Config {

    @Primary
    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                            final @Qualifier("db2-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.sf.apireaddata.dao.db2.domain")
                .properties(singletonMap("hibernate.dialect","org.hibernate.dialect.OracleDialect"))
                .properties(singletonMap("show-sql", "true"))
                .persistenceUnit("db2Db")
                .build();
    }

    @Primary
    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory")
                                                                      EntityManagerFactory db2EntityManagerFactory) {
        return new JpaTransactionManager(db2EntityManagerFactory);
    }

}