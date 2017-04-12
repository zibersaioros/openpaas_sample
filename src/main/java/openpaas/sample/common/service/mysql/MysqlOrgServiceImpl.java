package openpaas.sample.common.service.mysql;

import java.util.List;

import openpaas.sample.common.domain.mysql.MysqlOrg;
import openpaas.sample.common.repository.mysql.MysqlOrgRepository;
import openpaas.sample.common.service.common.OrgService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("mysqlOrgService")
@Transactional(readOnly=true, value="mysqlTransactionManager")
public class MysqlOrgServiceImpl implements OrgService<MysqlOrg>{

	@Autowired(required=false)
	private MysqlOrgRepository orgRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static Logger logger = LoggerFactory.getLogger(MysqlOrgServiceImpl.class);

	
	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public MysqlOrg add(MysqlOrg org) {
		return orgRepository.save(org);
	}
	
	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public MysqlOrg modify(MysqlOrg org) {
		MysqlOrg managedOrg = getOrg(org.getId());
		modelMapper.map(org, managedOrg);
		return orgRepository.saveAndFlush(managedOrg);
	}

	@Override
	public List<MysqlOrg> getOrgs() {
		return orgRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false, value="mysqlTransactionManager")
	public void removeOrg(String id) {
		
		orgRepository.delete(id);
	}
	
	@Override
	public long count(){ 
		return orgRepository.count();
	}

	@Override
	public MysqlOrg getOrg(String id) {
		return orgRepository.findOne(id);
	}


	
	
}
