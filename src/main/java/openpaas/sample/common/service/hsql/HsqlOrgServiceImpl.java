package openpaas.sample.common.service.hsql;

import java.util.List;

import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.repository.hsql.HsqlOrgRepository;
import openpaas.sample.common.service.common.OrgService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("hsqlOrgService")
@Transactional(readOnly=true, value="hsqlTransactionManager")
public class HsqlOrgServiceImpl implements OrgService<HsqlOrg>{

	@Autowired
	private HsqlOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static Logger logger = LoggerFactory.getLogger(HsqlOrgServiceImpl.class);

	
	@Override
	@Transactional(readOnly=false)
	public HsqlOrg add(HsqlOrg org) {
		return orgRepository.save(org);
	}
	
	@Override
	@Transactional(readOnly=false)
	public HsqlOrg modify(HsqlOrg org) {
		HsqlOrg managedOrg = getOrg(org.getId());
		modelMapper.map(org, managedOrg);
		return orgRepository.saveAndFlush(managedOrg);
	}

	@Override
	public List<HsqlOrg> getOrgs() {
		return orgRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false)
	public void removeOrg(String id) {
		
		orgRepository.delete(id);
	}
	
	@Override
	public long count(){ 
		return orgRepository.count();
	}

	@Override
	public HsqlOrg getOrg(String id) {
		return orgRepository.findOne(id);
	}


	
	
}
