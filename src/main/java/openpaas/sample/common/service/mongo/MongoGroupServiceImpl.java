package openpaas.sample.common.service.mongo;

import java.util.Date;
import java.util.List;

import openpaas.sample.common.domain.mongo.MongoGroup;
import openpaas.sample.common.domain.mongo.MongoOrg;
import openpaas.sample.common.repository.mongo.MongoGroupRepository;
import openpaas.sample.common.repository.mongo.MongoOrgRepository;
import openpaas.sample.common.service.common.AbstractGroupService;
import openpaas.sample.web.controller.support.GroupDTO;

import org.javaswift.joss.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mongoGroupService")
public class MongoGroupServiceImpl extends AbstractGroupService<MongoGroup>{

	@Autowired(required=false)
	MongoGroupRepository groupRepository;
	
	@Autowired(required=false)
	MongoOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired(required=false)
	Account account;
	
	
	@Override
	public MongoGroup add(GroupDTO groupDTO) {
		
		MongoGroup group = modelMapper.map(groupDTO, MongoGroup.class);
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		group.setCreated(new Date());
		return groupRepository.save(group);
	}

	@Override
	public MongoGroup modify(GroupDTO groupDTO) {
		
		MongoGroup group = groupRepository.findOne(groupDTO.getId());
		deleteOldImage(account, group.getThumb_img_path(), groupDTO.getThumb_img_path());
		
		modelMapper.map(groupDTO, group);
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		group.setModified(new Date());
		return groupRepository.save(group);
	}

	@Override
	public MongoGroup saveInInitPopulator(MongoGroup group, String orgId) {
		if(group.getSuperior() != null && !group.getSuperior().equals(""))
			group.setParent(groupRepository.findByLabel(group.getSuperior()));
		MongoOrg org = orgRepository.findOne(orgId);
		group.setOrg(org);
		group.setCreated(new Date());
		return groupRepository.save(group);	
	}

	@Override
	public List<MongoGroup> getGroups(String orgId) {
		return groupRepository.findByOrgId(orgId);
	}

	@Override
	public MongoGroup getGroup(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	public void removeGroup(String id) {
		deleteRecursive(id);
		groupRepository.delete(id);
	}

	@Override
	public long count() {
		return groupRepository.count();
	}

	@Override
	public MongoGroup findByLabel(String label) {
		return groupRepository.findByLabel(label);
	}

	private void deleteRecursive(String id){
		List<MongoGroup> deletedList = groupRepository.deleteByParent_Id(id);
		if(deletedList != null && deletedList.size() > 0){
			for(MongoGroup group : deletedList){
				deleteRecursive(group.getId());
			}
		}
	}
}
