package openpaas.sample.common.config.service;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.repository.mongo.MongoDBPopulator;
import openpaas.sample.common.repository.mongo.MongoGroupRepository;
import openpaas.sample.common.service.mongo.MongoGroupServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile(ServiceType.MONGODB)
@Configuration
@EnableMongoRepositories(basePackageClasses={MongoGroupRepository.class})
@ComponentScan(basePackageClasses={MongoDBPopulator.class, MongoGroupServiceImpl.class})
public class CloudMongoConfig extends AbstractCloudConfig {

	private static Logger logger = LoggerFactory.getLogger(CloudMongoConfig.class);

    @Bean
	public MongoDbFactory mongoDbFactory() throws Exception{
    	return connectionFactory().mongoDbFactory();
	}
    
    
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
    	return new MongoTemplate(mongoDbFactory);
    }
    
}
