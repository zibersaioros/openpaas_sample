package openpaas.sample.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import openpaas.sample.common.config.service.ServiceConst.RabbitConst;
import openpaas.sample.common.config.service.ServiceConst.State;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.service.common.GroupServiceDeligate;
import openpaas.sample.common.service.common.OrgServiceDeligate;
import openpaas.sample.common.service.common.RabbitMQService;
import openpaas.sample.web.controller.support.GroupDTO;
import openpaas.sample.web.controller.support.OrgDTO;


@Controller
public class OrgController {
	
	private static Logger logger = LoggerFactory.getLogger(OrgController.class);
	
	@Autowired
	OrgServiceDeligate orgService;
	
	@Autowired
	GroupServiceDeligate groupService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired(required=false)
	@Qualifier("rabbitAdmin")
	RabbitAdmin rabbitAdmin;
	
	@Autowired(required=false)
	@Qualifier("rabbitMQService")
	RabbitMQService rabbitMQService;
	
	
//	@Autowired
//	Gson gson;
	
	@RequestMapping(value="/org-chart/{id}/{dbType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrgChart(@PathVariable("id") String id, @PathVariable("dbType") String dbType){
		Map<String, Object> responseMap = new HashMap<String, Object>();

		// org 셋팅
		Org org = orgService.getOrg(id, dbType);
		responseMap.put("org", modelMapper.map(org, OrgDTO.Response.class)) ;
		
		//groups 셋팅
		List<Group> groups = groupService.getGroups(id, dbType);
		responseMap.put("groups", modelMapper.map(groups, new TypeToken<List<GroupDTO.Response>>(){}.getType()));
	
		return responseMap;
	}
	
	@RequestMapping(value="/orgs/{dbType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrgs(@PathVariable String dbType){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		//orgs 셋팅
		List<Org> orgs = orgService.getOrgs(dbType);
		responseMap.put("orgs", modelMapper.map(orgs, new TypeToken<List<OrgDTO.Response>>(){}.getType()));
		
		return responseMap;
	}
	
	@RequestMapping(value="/orgs/{id}/{dbType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrg(@PathVariable("id") String id, @PathVariable("dbType") String dbType){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		//org 셋팅
		Org org = orgService.getOrg(id, dbType);
		responseMap.put("org", modelMapper.map(org, OrgDTO.Response.class));
		
		return responseMap;
	}
	
	@RequestMapping(value="/orgs/{dbType}", method=RequestMethod.POST)
	@ResponseBody
//	@ResponseStatus(HttpStatus.CREATED)
	public OrgDTO.Response addOrg(@RequestBody @Valid Org org, @PathVariable String dbType){
		Org savedOrg = orgService.add(org, dbType);
		return modelMapper.map(savedOrg, OrgDTO.Response.class);
	}
	
	@RequestMapping(value="/orgs/{id}/{dbType}", method=RequestMethod.PUT)
	@ResponseBody
	public OrgDTO.Response modifyOrg(@RequestBody @Valid Org org, @PathVariable("id") String id, @PathVariable("dbType") String dbType){
		org.setId(id);
		Org managedOrg = orgService.modify(org, dbType);
		return modelMapper.map(managedOrg, OrgDTO.Response.class);
	}
	
	
	
	@RequestMapping(value="/orgs/{id}/{dbType}", method={RequestMethod.DELETE})
	@ResponseBody
	public Map<String, Object> removeOrg(@PathVariable("id") String id, @PathVariable("dbType") String dbType){
		orgService.removeOrg(id, dbType);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("id", id);
		return responseMap;
	}
	
	/**
	 *	DB 타입과 조직의 id에 해당하는 Queue에 변화된 상태가 있는지 조회한다.
	 *
	 * @param id
	 * @param dbType
	 * @return Map 형태로 상태를 반환
	 */
	
	@RequestMapping(value="/org-chart/{id}/status/{dbType}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOrgStatus(@PathVariable("id") String id, @PathVariable("dbType") String dbType){
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String result = null;
		if(rabbitMQService != null)
			result = rabbitMQService.requestQueue(dbType, id);
		
		if(result == null)
			result = State.NO_CHANGES;
		logger.info(" Received '" + result + "'");
		
		responseMap.put("status",result );
	
		return responseMap;
	}

}
