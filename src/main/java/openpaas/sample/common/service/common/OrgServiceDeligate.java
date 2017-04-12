package openpaas.sample.common.service.common;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.config.service.ServiceConst.State;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.domain.cubrid.CubridOrg;
import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.domain.mongo.MongoOrg;
import openpaas.sample.common.domain.mysql.MysqlOrg;
import openpaas.sample.web.controller.ManageController;


@Service
public class OrgServiceDeligate{
	
	@Autowired(required=false)
	@Qualifier("hsqlOrgService")
	OrgService<HsqlOrg> hsqlOrgService;
	
	@Autowired(required=false)
	@Qualifier("mysqlOrgService")
	OrgService<MysqlOrg> mysqlOrgService;
	
	@Autowired(required=false)
	@Qualifier("cubridOrgService")
	OrgService<CubridOrg> cubridOrgService;
	
	@Autowired(required=false)
	@Qualifier("mongoOrgService")
	OrgService<MongoOrg> mongoOrgService;
	
	@Autowired(required=false)
	@Qualifier("mysqlQueueTemplate")
	private volatile RabbitTemplate mysqlRabbitTemplate;
	
	@Autowired(required=false)
	@Qualifier("cubridQueueTemplate")
	private volatile RabbitTemplate cubridRabbitTemplate;
	
	@Autowired(required=false)
	@Qualifier("mongoQueueTemplate")
	private volatile RabbitTemplate mongoRabbitTemplate;
	
	@Autowired(required=false)
	@Qualifier("rabbitMQService")
	RabbitMQService rabbitMQService;
	
	@Autowired
	ModelMapper modelMapper;
	

	
	private <D> D map(Object source, Class<D> destination){
		return modelMapper.map(source, destination);
	}
	private <D> D map(Object source, Type destination){
		return modelMapper.map(source, destination);
	}
	

	public Org add(Org org, String dbType) {
		
		Org orgResult;
		
		switch (dbType) {
		case ServiceType.MYSQL:
			orgResult = map(mysqlOrgService.add(map(org, MysqlOrg.class)), Org.class);
			break;
		case ServiceType.CUBRID:
			orgResult = map(cubridOrgService.add(map(org, CubridOrg.class)), Org.class);
			break;
		case ServiceType.MONGODB:
			orgResult = map(mongoOrgService.add(map(org, MongoOrg.class)), Org.class);
			break;
		default:
			return map(hsqlOrgService.add(map(org, HsqlOrg.class)), Org.class);
		}
		
		if(rabbitMQService != null)
			rabbitMQService.createQueue(dbType, orgResult.getId());
		return orgResult;
	}

	public Org modify(Org org, String dbType) {
		Org orgResult;
		
		switch (dbType) {
		case ServiceType.MYSQL:
			orgResult = map(mysqlOrgService.modify(map(org, MysqlOrg.class)), Org.class);
			break;
		case ServiceType.CUBRID:
			orgResult = map(cubridOrgService.modify(map(org, CubridOrg.class)), Org.class);
			break;
		case ServiceType.MONGODB:
			orgResult = map(mongoOrgService.modify(map(org, MongoOrg.class)), Org.class);
			break;
		default:
			return map(hsqlOrgService.modify(map(org, HsqlOrg.class)), Org.class);
		}
		
		if(rabbitMQService != null)
			rabbitMQService.inputQueue(dbType, orgResult.getId(), State.ORG_UPDATED);
		return orgResult;
	}

	public List<Org> getOrgs(String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return map(mysqlOrgService.getOrgs(), new TypeToken<List<Org>>(){}.getType());
		case ServiceType.CUBRID:
			return map(cubridOrgService.getOrgs(), new TypeToken<List<Org>>(){}.getType());
		case ServiceType.MONGODB:
			return map(mongoOrgService.getOrgs(), new TypeToken<List<Org>>(){}.getType());
		default:
			return map(hsqlOrgService.getOrgs(), new TypeToken<List<Org>>(){}.getType());
		}
	}

	public void removeOrg(String id, String dbType) {
		
		if(rabbitMQService != null)
			rabbitMQService.inputQueue(dbType, id, State.ORG_DELETED);
		
		switch (dbType) {
		case ServiceType.MYSQL:
			mysqlOrgService.removeOrg(id);
			break;
		case ServiceType.CUBRID:
			cubridOrgService.removeOrg(id);
			break;
		case ServiceType.MONGODB:
			mongoOrgService.removeOrg(id);
			break;
		default:
			hsqlOrgService.removeOrg(id);
			break;
		}
	}

	public long count(String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return mysqlOrgService.count();
		case ServiceType.CUBRID:
			return cubridOrgService.count();
		case ServiceType.MONGODB:
			return mongoOrgService.count();
		default:
			return hsqlOrgService.count();
		}
	}

	public Org getOrg(String id, String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return map(mysqlOrgService.getOrg(id), Org.class);
		case ServiceType.CUBRID:
			return map(cubridOrgService.getOrg(id), Org.class);
		case ServiceType.MONGODB:
			return map(mongoOrgService.getOrg( id), Org.class);
		default:
			return map(hsqlOrgService.getOrg( id), Org.class);
		}
	}
}
