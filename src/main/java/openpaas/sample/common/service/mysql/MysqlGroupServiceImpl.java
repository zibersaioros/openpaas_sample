package openpaas.sample.common.service.mysql;

import java.util.List;

import openpaas.sample.common.domain.mysql.MysqlGroup;
import openpaas.sample.common.domain.mysql.MysqlOrg;
import openpaas.sample.common.repository.mysql.MysqlGroupRepository;
import openpaas.sample.common.repository.mysql.MysqlOrgRepository;
import openpaas.sample.common.service.common.AbstractGroupService;
import openpaas.sample.web.controller.support.GroupDTO;

import org.javaswift.joss.model.Account;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mysqlGroupService")
@Transactional(readOnly=true, value="mysqlTransactionManager")
public class MysqlGroupServiceImpl extends AbstractGroupService<MysqlGroup>{

	@Autowired(required=false)
	MysqlGroupRepository groupRepository;
	
	@Autowired(required=false)
	MysqlOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired(required=false)
	Account account;

	private static Logger logger = LoggerFactory.getLogger(MysqlGroupServiceImpl.class);
	
	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager" )
	public MysqlGroup add(GroupDTO groupDTO) {
		MysqlGroup group = modelMapper.map(groupDTO, MysqlGroup.class);
		
		group.setOrg(orgRepository.findOne( groupDTO.getOrg_id()));
		
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		
		
		return groupRepository.save(group);
	}
	

	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public MysqlGroup modify(GroupDTO groupDTO) {
		MysqlGroup group = groupRepository.findOne(groupDTO.getId());
		deleteOldImage(account, group.getThumb_img_path(), groupDTO.getThumb_img_path());
		modelMapper.map(groupDTO, group);
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		if(groupDTO.getParent_id() != null && !groupDTO.getParent_id().equals(""))
			group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
		else
			group.setParent(null);
		
		return groupRepository.saveAndFlush(group);
	}

	
	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public MysqlGroup saveInInitPopulator(MysqlGroup group, String orgId) {
		if(group.getSuperior() != null && !group.getSuperior().equals(""))
			group.setParent(groupRepository.findByLabel(group.getSuperior()));
		MysqlOrg org = orgRepository.findOne(orgId);
		group.setOrg(org);
		
		return groupRepository.save(group);
	}

	@Override
	public List<MysqlGroup> getGroups(String orgId) {
		return groupRepository.findByOrgId(orgId);
	}

	@Override
	public MysqlGroup getGroup(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public void removeGroup(String id) {
		MysqlGroup group = groupRepository.findOne(id);
		deleteOldImage(account, group.getThumb_img_path(), "");
		groupRepository.delete(id);
	}

	@Override
	public long count() {
		return groupRepository.count();
	}

	@Override
	public MysqlGroup findByLabel(String label) {
		return groupRepository.findByLabel(label);
	}

	
}
