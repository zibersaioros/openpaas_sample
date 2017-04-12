package openpaas.sample.common.config.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import openpaas.sample.common.config.service.ServiceConst.ServiceId;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.service.rabbitmq.RabbitMQServiceImpl;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.rabbitmq.client.ConnectionFactory;

/**
 *	 RabbitMQ 서비스와 연결하기 위한 설정 파일입니다.
 */
@Profile(ServiceType.RABBITMQ)
@Configuration
@ComponentScan(basePackageClasses={RabbitMQServiceImpl.class})
public class CloudRabbitConfig extends AbstractCloudConfig{

	/**
	 * RabbitMQ 서비스와 연결하기 위해 Factory를 설정하고 생성합니다.
	 * 
	 * @return ConnectionFactory
	 */
	@Bean(name = "rabbitmqConnectionFactory")
	@Primary
	public CachingConnectionFactory rabbitConnectionFactory() {
		CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();	
        AmqpServiceInfo serviceInfo = (AmqpServiceInfo)cloud.getServiceInfo(ServiceId.RABBITMQ);
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(serviceInfo.getHost());
		connectionFactory.setPort(5671);
	    connectionFactory.setUsername(serviceInfo.getUserName());
	    connectionFactory.setPassword(serviceInfo.getPassword());
	    connectionFactory.setVirtualHost(serviceInfo.getVirtualHost());
		try {
			connectionFactory.useSslProtocol("TLS");
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
			
		return new CachingConnectionFactory(connectionFactory);
	}
	
	/**
	 * RabbitMQ에 tamplate를 생성합니다.
	 * 
	 * @param connectionFactory
	 * @return RabbitTemplate
	 */
	@Bean(name="dynamicExchange")
	public RabbitTemplate devideQueueTemplate(@Qualifier("rabbitmqConnectionFactory") CachingConnectionFactory connectionFactory){
		RabbitTemplate rt = new RabbitTemplate();
		rt.setConnectionFactory(connectionFactory);
		return rt;
	}
	
	/**
	 * 생성된 Factory를 받아서 RabbitMQ의 admin을 생성합니다.
	 * 
	 * @param connectionFactory
	 * @return RabbitAdmin
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(@Qualifier("rabbitmqConnectionFactory") CachingConnectionFactory connectionFactory) {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);		
		return admin;
	}
}
