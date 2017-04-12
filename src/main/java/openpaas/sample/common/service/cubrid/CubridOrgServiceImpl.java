package openpaas.sample.common.service.cubrid;

import java.util.List;

import openpaas.sample.common.domain.cubrid.CubridOrg;
import openpaas.sample.common.repository.cubrid.CubridOrgRepository;
import openpaas.sample.common.service.common.OrgService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("cubridOrgService")
@Transactional(readOnly=true, value="cubridTransactionManager")
public class CubridOrgServiceImpl implements OrgService<CubridOrg>{

	@Autowired
	private CubridOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static Logger logger = LoggerFactory.getLogger(CubridOrgServiceImpl.class);

	
	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public CubridOrg add(CubridOrg org) {
		return orgRepository.save(org);
	}
	
	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public CubridOrg modify(CubridOrg org) {
		CubridOrg managedOrg = getOrg(org.getId());
		modelMapper.map(org, managedOrg);
		return orgRepository.saveAndFlush(managedOrg);
	}

	@Override
	public List<CubridOrg> getOrgs() {
		return orgRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, value="cubridTransactionManager")
	public void removeOrg(String id) {
		
		orgRepository.delete(id);
	}
	
	@Override
	public long count(){ 
		return orgRepository.count();
	}

	@Override
	public CubridOrg getOrg(String id) {
		return orgRepository.findOne(id);
	}

}
