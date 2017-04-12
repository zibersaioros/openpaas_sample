package openpaas.sample.common.config.service;

import javax.sql.DataSource;

import openpaas.sample.common.config.service.ServiceConst.ServiceId;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.mysql.MysqlOrg;
import openpaas.sample.common.repository.mysql.MysqlOrgRepository;
import openpaas.sample.common.service.common.RabbitMQService;
import openpaas.sample.common.service.mysql.MysqlOrgServiceImpl;

import org.hibernate.dialect.MySQLDialect;
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

@Profile(ServiceType.MYSQL)
@Configuration
@EnableJpaRepositories(basePackageClasses={MysqlOrgRepository.class}, entityManagerFactoryRef="mysqlEntityManagerFactory", transactionManagerRef="mysqlTransactionManager")
@ComponentScan(basePackageClasses={MysqlOrgServiceImpl.class})
public class CloudMysqlConfig extends AbstractCloudConfig {
	
	@Autowired(required=false)
	@Qualifier("rabbitMQService")
	RabbitMQService rabbitMQService;
	

	@Bean
	public DataSource mysqlDataSource(){
		return connectionFactory().dataSource();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(MySQLDialect.class.getName());
				
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(MysqlOrg.class.getPackage().getName());
		factory.setDataSource(mysqlDataSource());
		
		factory.afterPropertiesSet();
		
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager mysqlTransactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager(mysqlEntityManagerFactory().getObject());
		return transactionManager;
	}
	
	
	@Bean
	public DataSourceInitializer mysqlDataSourceInitializer() {

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		
		databasePopulator.addScripts(new ClassPathResource("schema_mysql.sql")
				, new ClassPathResource("data_mysql.sql"));
		
		if(rabbitMQService != null)
			rabbitMQService.createQueue(ServiceType.MYSQL, "1");
		databasePopulator.setIgnoreFailedDrops(true);
//		databasePopulator.setContinueOnError(true);

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(mysqlDataSource());
		initializer.setDatabasePopulator(databasePopulator);

		return initializer;
	}
}
