package openpaas.sample.common.config.service;

import javax.sql.DataSource;

import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.repository.hsql.HsqlOrgRepository;
import openpaas.sample.common.service.hsql.HsqlOrgServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile(SpringApplicationContextInitializer.IN_MEMORY_PROFILE)
@EnableJpaRepositories(basePackageClasses={HsqlOrgRepository.class}, entityManagerFactoryRef="hsqlEntityManagerFactory", transactionManagerRef="hsqlTransactionManager")
@ComponentScan(basePackageClasses={HsqlOrgServiceImpl.class})
public class InMemoryDBConfig {

	@Bean
	public DataSource hsqlDataSource(){
		return new EmbeddedDatabaseBuilder()
				.setName("Organization") // DB 이름 설정
				.setType(EmbeddedDatabaseType.HSQL) //DB 종류 설정
				.addScript("schema_hsqldb.sql")
				.addScript("data.sql")
				.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean hsqlEntityManagerFactory(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(HsqlOrg.class.getPackage().getName());
		factory.setDataSource(hsqlDataSource());
		factory.afterPropertiesSet();

		return factory;
	}

	@Bean
	public PlatformTransactionManager hsqlTransactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager(hsqlEntityManagerFactory().getObject());
		return transactionManager;
	}
}
