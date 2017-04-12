package openpaas.sample.common.config.service;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Profile(ServiceType.REDIS)
@Configuration
public class CloudRedisConfig extends AbstractCloudConfig {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() throws Exception{
		return connectionFactory().redisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		redisTemplate.setDefaultSerializer(stringSerializer);
		
		return redisTemplate;
	}
}

