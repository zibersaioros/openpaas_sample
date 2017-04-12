package openpaas.sample.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import openpaas.sample.common.domain.Group;
import openpaas.sample.common.service.common.GroupServiceDeligate;
import openpaas.sample.common.service.common.OrgServiceDeligate;
import openpaas.sample.web.controller.support.GroupDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orgs/{orgId}/groups")
public class GroupController {
	
	private static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	OrgServiceDeligate orgService;
	
	@Autowired
	GroupServiceDeligate groupService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@RequestMapping(value="/{dbType}", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getGroups(@PathVariable("orgId") String orgId, @PathVariable("dbType") String dbType){
		List<Group> groups = groupService.getGroups(orgId, dbType);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("groups", modelMapper.map(groups, new TypeToken<List<GroupDTO.Response>>(){}.getType()));
		return responseMap;
	}
	
	@RequestMapping(value="/{groupId}/{dbType}", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getGroup(@PathVariable("groupId") String groupId, @PathVariable("dbType") String dbType ){
		Group group = groupService.getGroup(groupId, dbType);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("group", modelMapper.map(group, GroupDTO.Response.class));
		return responseMap;
	}
	
	@RequestMapping(value="/{dbType}", method=RequestMethod.POST)
	@ResponseBody
	public GroupDTO.Response addGroup(@RequestBody @Valid GroupDTO dto, @PathVariable("dbType") String dbType){
		Group group = groupService.add(dto, dbType);
		return modelMapper.map(group, GroupDTO.Response.class);
	}
	
	@RequestMapping(value="/{groupId}/{dbType}", method=RequestMethod.PUT)
	@ResponseBody
	public GroupDTO.Response modifyGroup(@RequestBody @Valid GroupDTO dto
			, @PathVariable("groupId") String groupId, @PathVariable("dbType") String dbType ){
		dto.setId(groupId);
		Group managedGroup = groupService.modify(dto, dbType);
		return modelMapper.map(managedGroup, GroupDTO.Response.class);
	}
	
	@RequestMapping(value="/{groupId}/{dbType}", method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> removeGroup(@PathVariable("groupId") String groupId, @PathVariable("dbType") String dbType){
		groupService.removeGroup(groupId, dbType);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("id", groupId);
		return responseMap;
	}
}
