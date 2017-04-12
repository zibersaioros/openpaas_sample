package openpaas.sample.common.service.rabbitmq;

import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import openpaas.sample.common.config.service.ServiceConst.RabbitConst;
import openpaas.sample.common.service.common.OrgServiceDeligate;
import openpaas.sample.common.service.common.RabbitMQService;

@Service("rabbitMQService")
public class RabbitMQServiceImpl implements RabbitMQService {
	
	private static Logger logger = LoggerFactory.getLogger(RabbitMQServiceImpl.class);
	
	@Autowired
	RabbitAdmin rabbitAdmin;
	
	@Resource(name = "dynamicExchange" )
	private volatile RabbitTemplate dynamicRabbitTemplate;

	/**
	 * DB 타입과 해당 조직의 id를 받아서 Queue를 생성한다.
	 * 
	 * @param id
	 * @param dbType
	 * 
	 */
	@Override
	public void createQueue(String type, String id) {
		
		String queueName = makeQueueName(type,id);
		rabbitAdmin.declareQueue(new Queue(queueName));
		rabbitAdmin.getRabbitTemplate().setRoutingKey(queueName);
		rabbitAdmin.getRabbitTemplate().afterPropertiesSet();
		logger.info("Make : " +  queueName);
		
	}

	/**
	 * DB 타입과 조직의 id에 해당하는 Queue를 찾아 메시지를 삽입한다.
	 * 
 	 * @param id
	 * @param dbType
	 * 
	 */
	@Override
	public void inputQueue(String type, String id, String message) {
		String queueName = makeQueueName(type,id);
		logger.info("Input : " +queueName);
		dynamicRabbitTemplate.convertAndSend(queueName, message);
	}

	/**
	 * DB 타입과 조직의 id에 해당하는 Queue의 내용을 받아온다.
	 *
	 * @param id
	 * @param dbType
	 * @return Queue에 들어 있는 상태값
	 * 
	 */
	@Override
	public String requestQueue(String type, String id) {
		String queueName = makeQueueName(type,id);
		
		logger.info("Get : " +queueName);
		String result = (String) dynamicRabbitTemplate.receiveAndConvert(queueName);
		rabbitAdmin.purgeQueue(queueName, true);
		return result;
	}

	/**
	 *	DB 타입과 해당 조직의 id를 받아서 Queue 이름 생성
	 *
	 * @param id
	 * @param dbType
	 * @return Queue의 이름
	 * 
	 */
	String makeQueueName(String type, String id) {
		return id + "_" + type;
	}
}
