package application.security.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@EnableMongoRepositories(basePackages = "application.security", mongoTemplateRef = "securityMongoTemplate")
public class SecurityDataSourceConfig {

	@Primary
	@Bean(name = "securityMongoProperties")
	@ConfigurationProperties("security.spring.data.mongodb")
	public MongoProperties dataSourceProperties() {
		return new MongoProperties();
	}

	@Primary
	@Bean(name = "securityMongoDatabaseFactory")
	public MongoDatabaseFactory mongoDatabaseFactory(
			@Qualifier("securityMongoProperties") MongoProperties dataSourceProperties) {
		return new SimpleMongoClientDatabaseFactory(dataSourceProperties.getUri());
	}

	@Primary
	@Bean(name = "securityMongoTemplate")
	public MongoTemplate mongoTemplate(
			@Qualifier("securityMongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTemplate(mongoDatabaseFactory);
	}

	@Primary
	@Bean(name = "securityMongoTransactionManager")
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
}