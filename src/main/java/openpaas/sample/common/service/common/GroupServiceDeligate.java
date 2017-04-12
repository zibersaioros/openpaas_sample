package openpaas.sample.common.service.common;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.config.service.ServiceConst.State;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.cubrid.CubridGroup;
import openpaas.sample.common.domain.hsql.HsqlGroup;
import openpaas.sample.common.domain.mongo.MongoGroup;
import openpaas.sample.common.domain.mysql.MysqlGroup;
import openpaas.sample.web.controller.support.GroupDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceDeligate {
	
	@Autowired(required=false)
	@Qualifier("hsqlGroupService")
	GroupService<HsqlGroup> hsqlGroupService;
	
	@Autowired(required=false)
	@Qualifier("mysqlGroupService")
	GroupService<MysqlGroup> mysqlGroupService;
	
	@Autowired(required=false)
	@Qualifier("cubridGroupService")
	GroupService<CubridGroup> cubridGroupService;

	@Autowired(required=false)
	@Qualifier("mongoGroupService")
	GroupService<MongoGroup> mongoGroupService;
	
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

	
	public Group add(GroupDTO groupDTO, String dbType) {
		
		Group groupResult;
		
		switch (dbType) {
		case ServiceType.MYSQL:
			groupResult = map(mysqlGroupService.add(groupDTO), Group.class);
			break;
		case ServiceType.CUBRID:
			groupResult = map(cubridGroupService.add(groupDTO), Group.class);
			break;
		case ServiceType.MONGODB:
			groupResult = map(mongoGroupService.add(groupDTO), Group.class);
			break;
		default:
			return map(hsqlGroupService.add(groupDTO), Group.class);
		}
		
		//rabbitMQ insert
		if(rabbitMQService != null)
			rabbitMQService.inputQueue(dbType, groupResult.getOrg().getId(), State.GROUP_ADDED);
		
		return groupResult;
	}

	public Group modify(GroupDTO groupDTO, String dbType) {
		
		Group groupResult;

		switch (dbType) {
		case ServiceType.MYSQL:
			groupResult = map(mysqlGroupService.modify(groupDTO), Group.class);
			break;
		case ServiceType.CUBRID:
			groupResult = map(cubridGroupService.modify(groupDTO), Group.class);
			break;
		case ServiceType.MONGODB:
			groupResult = map(mongoGroupService.modify(groupDTO), Group.class);
			break;
		default:
			return map(hsqlGroupService.modify(groupDTO), Group.class);
		}
		
		if(rabbitMQService != null)
			rabbitMQService.inputQueue(dbType, groupResult.getOrg().getId(), State.GROUP_UPDATED);
		
		return groupResult;
		
	}

	public Group saveInInitPopulator(Group group, String orgId, String dbType) {
		Group groupResult;
		
		if(rabbitMQService != null)
			rabbitMQService.createQueue(dbType, orgId);
		
		switch (dbType) {
		case ServiceType.MYSQL:
			groupResult = map(mysqlGroupService.saveInInitPopulator(map(group, MysqlGroup.class), orgId), Group.class);
			break;
		case ServiceType.CUBRID:
			groupResult = map(cubridGroupService.saveInInitPopulator(map(group, CubridGroup.class), orgId), Group.class);
			break;
		case ServiceType.MONGODB:
			groupResult = map(mongoGroupService.saveInInitPopulator(map(group, MongoGroup.class), orgId), Group.class);
			break;
		default:
			return map(hsqlGroupService.saveInInitPopulator(map(group, HsqlGroup.class), orgId), Group.class);
		}
		return groupResult;
	}

	public List<Group> getGroups(String orgId, String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return map(mysqlGroupService.getGroups(orgId), new TypeToken<List<Group>>(){}.getType());
		case ServiceType.CUBRID:
			return map(cubridGroupService.getGroups(orgId), new TypeToken<List<Group>>(){}.getType());
		case ServiceType.MONGODB:
			return map(mongoGroupService.getGroups(orgId), new TypeToken<List<Group>>(){}.getType());
		default:
			return map(hsqlGroupService.getGroups(orgId), new TypeToken<List<Group>>(){}.getType());
		}
	}

	public Group getGroup(String id, String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return map(mysqlGroupService.getGroup(id), Group.class);
		case ServiceType.CUBRID:
			return map(cubridGroupService.getGroup(id), Group.class);
		case ServiceType.MONGODB:
			return map(mongoGroupService.getGroup(id), Group.class);
		default:
			return map(hsqlGroupService.getGroup(id), Group.class);
		}
	}

	public void removeGroup(String id, String dbType) {
		
		Group group = getGroup(id, dbType);
		
		switch (dbType) {
		case ServiceType.MYSQL:
			mysqlGroupService.removeGroup(id);
			break;
		case ServiceType.CUBRID:
			cubridGroupService.removeGroup(id);
			break;
		case ServiceType.MONGODB:
			mongoGroupService.removeGroup(id);
			break;
		default:
			hsqlGroupService.removeGroup(id);
			break;
		}
		
		if(rabbitMQService != null)
			rabbitMQService.inputQueue(dbType, group.getOrg().getId(), State.GROUP_DELETED);
		
		
	}

	public long count(String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return mysqlGroupService.count();
		case ServiceType.CUBRID:
			return cubridGroupService.count();
		case ServiceType.MONGODB:
			return mongoGroupService.count();
		default:
			return hsqlGroupService.count();
		}
	}

	public Group findByLabel(String label, String dbType) {
		switch (dbType) {
		case ServiceType.MYSQL:
			return map(mysqlGroupService.findByLabel(label), Group.class);
		case ServiceType.CUBRID:
			return map(cubridGroupService.findByLabel(label), Group.class);
		case ServiceType.MONGODB:
			return map(mongoGroupService.findByLabel(label), Group.class);
		default:
			return map(hsqlGroupService.findByLabel(label), Group.class);
		}
	}
	

}
