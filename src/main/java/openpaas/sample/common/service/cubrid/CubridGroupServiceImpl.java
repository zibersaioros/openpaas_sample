package openpaas.sample.common.service.cubrid;

import java.util.List;

import openpaas.sample.common.domain.cubrid.CubridGroup;
import openpaas.sample.common.domain.cubrid.CubridOrg;
import openpaas.sample.common.repository.cubrid.CubridGroupRepository;
import openpaas.sample.common.repository.cubrid.CubridOrgRepository;
import openpaas.sample.common.service.common.AbstractGroupService;
import openpaas.sample.web.controller.support.GroupDTO;

import org.javaswift.joss.model.Account;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cubridGroupService")
@Transactional(readOnly=true, value="cubridTransactionManager")
public class CubridGroupServiceImpl extends AbstractGroupService<CubridGroup>{

	@Autowired
	CubridGroupRepository groupRepository;
	
	@Autowired
	CubridOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired(required=false)
	Account account;

	private static Logger logger = LoggerFactory.getLogger(CubridGroupServiceImpl.class);
	
	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public CubridGroup add(GroupDTO groupDTO) {
		CubridGroup group = modelMapper.map(groupDTO, CubridGroup.class);
		
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		
		if(groupDTO.getParent_id() != null){
			try {
				group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
			} catch (Exception e) {
				group.setParent(null);
			}
		}
		else
			group.setParent(null);
		
		return groupRepository.save(group);
	}
	

	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public CubridGroup modify(GroupDTO groupDTO) {
		CubridGroup group = groupRepository.findOne(groupDTO.getId());
		deleteOldImage(account, group.getThumb_img_path(), groupDTO.getThumb_img_path());
		modelMapper.map(groupDTO, group);
		group.setOrg(orgRepository.findOne(groupDTO.getOrg_id()));
		
		if(groupDTO.getParent_id() != null){
			try {
				group.setParent(groupRepository.findOne(groupDTO.getParent_id()));
			} catch (Exception e) {
				group.setParent(null);
			}
		}
		else
			group.setParent(null);
		
		return groupRepository.saveAndFlush(group);
	}

	
	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public CubridGroup saveInInitPopulator(CubridGroup group, String orgId) {
		if(group.getSuperior() != null && !group.getSuperior().equals(""))
			group.setParent(groupRepository.findByLabel(group.getSuperior()));
		else
			group.setParent(null);
		CubridOrg org = orgRepository.findOne(orgId);
		group.setOrg(org);
		
		//TODO 이미지 저장 로직 추가
		
		return groupRepository.save(group);
	}

	@Override
	public List<CubridGroup> getGroups(String orgId) {
		return groupRepository.findByOrgId(orgId);
	}

	@Override
	public CubridGroup getGroup(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public void removeGroup(String id) {
		CubridGroup group = groupRepository.findOne(id);
		deleteOldImage(account, group.getThumb_img_path(), "");
		groupRepository.delete(id);
	}

	@Override
	public long count() {
		return groupRepository.count();
	}

	@Override
	public CubridGroup findByLabel(String label) {
		return groupRepository.findByLabel(label);
	}

	
}
