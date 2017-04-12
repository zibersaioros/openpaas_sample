package openpaas.sample.common.config.service;

import javax.sql.DataSource;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.cubrid.CubridOrg;
import openpaas.sample.common.repository.cubrid.CubridOrgRepository;
import openpaas.sample.common.service.common.RabbitMQService;
import openpaas.sample.common.service.cubrid.CubridOrgServiceImpl;

import org.hibernate.dialect.CUBRIDDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cubrid.jdbc.driver.CUBRIDDataSource;

@Profile(ServiceType.CUBRID)
@Configuration
@EnableJpaRepositories(basePackageClasses={CubridOrgRepository.class}, entityManagerFactoryRef="cubridEntityManagerFactory", transactionManagerRef="cubridTransactionManager")
@ComponentScan(basePackageClasses={CubridOrgServiceImpl.class})
public class CloudCubridConfig {
	
	Logger logger = LoggerFactory.getLogger(AbstractCloudConfig.class);
	
	@Autowired(required=false)
	@Qualifier("rabbitMQService")
	RabbitMQService rabbitMQService;
	
	@Autowired
	Gson gson;

	@Bean
	public DataSource cubridDataSource() throws Exception{
		String vcap_services = System.getenv("VCAP_SERVICES");
        logger.info("VCAP_SERVICES = " + vcap_services);
        
        JsonObject jsonObj = gson.fromJson(vcap_services, JsonElement.class).getAsJsonObject();
        logger.info("vcap_service = " + jsonObj);
        
        JsonArray userPro = jsonObj.getAsJsonArray("CubridDB");
        jsonObj = userPro.get(0).getAsJsonObject().getAsJsonObject("credentials");
        String jdbcurl = jsonObj.get("jdbcurl").getAsString();
        
		CUBRIDDataSource dataSource = new CUBRIDDataSource();
		dataSource.setUrl(jdbcurl);
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean cubridEntityManagerFactory() throws Exception{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(CUBRIDDialect.class.getName());
				
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(CubridOrg.class.getPackage().getName());
		factory.setDataSource(cubridDataSource());
		
		factory.afterPropertiesSet();
		
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager cubridTransactionManager() throws Exception{
		JpaTransactionManager transactionManager = new JpaTransactionManager(cubridEntityManagerFactory().getObject());
		return transactionManager;
	}
	
	
	@Bean
	public DataSourceInitializer cubridDataSourceInitializer() throws Exception{

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		
		databasePopulator.addScripts(new ClassPathResource("schema_cubrid.sql")
				, new ClassPathResource("data_cubrid.sql"));
		
		if(rabbitMQService != null)
			rabbitMQService.createQueue(ServiceType.CUBRID, "1");
		databasePopulator.setIgnoreFailedDrops(true);
//		databasePopulator.setContinueOnError(true);

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(cubridDataSource());
		initializer.setDatabasePopulator(databasePopulator);

		return initializer;
	}
}
