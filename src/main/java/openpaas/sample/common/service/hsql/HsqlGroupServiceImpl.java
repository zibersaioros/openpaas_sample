package openpaas.sample.common.service.hsql;

import java.util.List;

import openpaas.sample.common.domain.hsql.HsqlGroup;
import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.repository.hsql.HsqlGroupRepository;
import openpaas.sample.common.repository.hsql.HsqlOrgRepository;
import openpaas.sample.common.service.common.AbstractGroupService;
import openpaas.sample.web.controller.support.GroupDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hsqlGroupService")
@Transactional(readOnly=true, value="hsqlTransactionManager")
public class HsqlGroupServiceImpl extends AbstractGroupService<HsqlGroup>{

	@Autowired
	HsqlGroupRepository groupRepository;
	
	@Autowired
	HsqlOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static Logger logger = LoggerFactory.getLogger(HsqlGroupServiceImpl.class);
	
	@Override
	@Transactional(readOnly=false)
	public HsqlGroup add(GroupDTO groupDTO) {
		HsqlGroup group = modelMapper.map(groupDTO, HsqlGroup.class);
		
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		
		
		return groupRepository.save(group);
	}
	

	@Override
	@Transactional(readOnly=false)
	public HsqlGroup modify(GroupDTO groupDTO) {
		HsqlGroup group = groupRepository.findOne(groupDTO.getId());
		modelMapper.map(groupDTO, group);
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		
		//TODO 이미지 저장 로직 추가
		
		return groupRepository.saveAndFlush(group);
	}

	
	@Override
	@Transactional(readOnly=false)
	public HsqlGroup saveInInitPopulator(HsqlGroup group, String orgId) {
		if(group.getSuperior() != null && !group.getSuperior().equals(""))
			group.setParent(groupRepository.findByLabel(group.getSuperior()));
		HsqlOrg org = orgRepository.findOne(orgId);
		group.setOrg(org);
		
		//TODO 이미지 저장 로직 추가
		
		return groupRepository.save(group);
	}

	@Override
	public List<HsqlGroup> getGroups(String orgId) {
		return groupRepository.findByOrgId(orgId);
	}

	@Override
	public HsqlGroup getGroup(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void removeGroup(String id) {
		groupRepository.delete(id);
	}

	@Override
	public long count() {
		return groupRepository.count();
	}

	@Override
	public HsqlGroup findByLabel(String label) {
		return groupRepository.findByLabel(label);
	}

	
}
