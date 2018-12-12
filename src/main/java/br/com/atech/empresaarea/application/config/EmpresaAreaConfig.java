package br.com.atech.empresaarea.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.yml")
public class EmpresaAreaConfig {

    @Value("${datasource.db-db1.url}")
    private String dataSourceDbdb1Url;
    @Value("${datasource.db-db1.username}")
    private String dataSourceDbdb1Username;
    @Value("${datasource.db-db1.password}")
    private String dataSourceDbdb1Password;
    @Value("${datasource.db-db2.url}")
    private String dataSourceDbdb2Url;
    @Value("${datasource.db-db2.username}")
    private String dataSourceDbdb2Username;
    @Value("${datasource.db-db2.password}")
    private String dataSourceDbdb2Password;
    @Value("${datasource.db-db2.driver-class-name}")
    private String dataSourceDbdb2DriverClassName;
    @Value("${datasource.db-db1.driver-class-name}")
    private String dataSourceDbdb1DriverClassName;

    public String getDataSourceDbdb1Url() {
        return dataSourceDbdb1Url;
    }

    public String getDataSourceDbdb1Username() {
        return dataSourceDbdb1Username;
    }

    public String getDataSourceDbdb1Password() {
        return dataSourceDbdb1Password;
    }

    public String getDataSourceDbdb2Url() {
        return dataSourceDbdb2Url;
    }

    public String getDataSourceDbdb2Username() {
        return dataSourceDbdb2Username;
    }

    public String getDataSourceDbdb2Password() {
        return dataSourceDbdb2Password;
    }

    public String getDataSourceDbdb2DriverClassName() {
        return dataSourceDbdb2DriverClassName;
    }

    public String getDataSourceDbdb1DriverClassName() {
        return dataSourceDbdb1DriverClassName;
    }

    @Primary
    @Bean(name = "db1-db")
    public DataSource db1DataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getDataSourceDbdb1DriverClassName());
        dataSource.setUrl(getDataSourceDbdb1Url());
        dataSource.setPassword(getDataSourceDbdb1Password());
        dataSource.setUsername(getDataSourceDbdb1Username());

                /*
        TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(timeZone);
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(getDataSourceDbdb1Url());
        dataSource.setUser(getDataSourceDbdb1Username());
        dataSource.setPassword(getDataSourceDbdb1Password());
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);*/
        return dataSource;
    }

    @Bean(name = "db2-db")
    public DataSource db2DataSource() throws SQLException {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getDataSourceDbdb2DriverClassName());
        dataSource.setUrl(getDataSourceDbdb2Url());
        dataSource.setPassword(getDataSourceDbdb2Password());
        dataSource.setUsername(getDataSourceDbdb2Username());

        /*
        TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(timeZone);
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(getDataSourceDbdb2Url());
        dataSource.setUser(getDataSourceDbdb2Username());
        dataSource.setPassword(getDataSourceDbdb2Password());
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        */
        return dataSource;
    }

    @Primary
    @Bean(name= "db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(@Qualifier("db1-db") DataSource db1ds){
        return new JdbcTemplate(db1ds);
    }

    @Bean(name= "db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2-db") DataSource db2ds){
        return new JdbcTemplate(db2ds);
    }

}
