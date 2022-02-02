package application.business.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "application.business",
        entityManagerFactoryRef = "businessEntityManagerFactory",
        transactionManagerRef = "businessTransactionManager")

public class BusinessDataSourceConfig {

    @Bean(name = "businessDataSourceProperties")
    @ConfigurationProperties(prefix = "business.datasource")
    public DataSourceProperties dataSourcePropertiesSql() {
        return new DataSourceProperties();
    }

    @Bean(name = "businessDataSource")
//    @Bean(name = "businessDataSourceProperties")
//    @ConfigurationProperties(prefix = "business.spring.datasource")
    public DataSource dataSource(@Qualifier("businessDataSourceProperties")
                                             DataSourceProperties dataSourceProperties) {
//    public DataSource dataSource() {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
//        return DataSourceBuilder.create().build();
    }

    @Bean(name = "businessEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean businessEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("businessDataSource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("application.business")
                .build();
    }

    @Bean(name = "businessTransactionManager")
    public PlatformTransactionManager businessTransactionManager(
            @Qualifier("businessEntityManagerFactory") EntityManagerFactory businessEntityManagerFactory) {
        return new JpaTransactionManager(businessEntityManagerFactory);
    }
}


