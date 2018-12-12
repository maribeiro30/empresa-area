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
        entityManagerFactoryRef = "saudeEntityManagerFactory",
        transactionManagerRef = "saudeTransactionManager",
        basePackages = "br.com.sf.apireaddata.dao.saude.repository"
)
@EnableTransactionManagement
public class Db2Config {

    @Primary
    @Bean(name = "saudeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean saudeEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                            final @Qualifier("saude-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("br.com.sf.apireaddata.dao.saude.domain")
                .properties(singletonMap("hibernate.dialect","org.hibernate.dialect.OracleDialect"))
                .properties(singletonMap("show-sql", "true"))
                .persistenceUnit("saudeDb")
                .build();
    }

    @Primary
    @Bean(name = "saudeTransactionManager")
    public PlatformTransactionManager saudeTransactionManager(@Qualifier("saudeEntityManagerFactory")
                                                                      EntityManagerFactory saudeEntityManagerFactory) {
        return new JpaTransactionManager(saudeEntityManagerFactory);
    }

}