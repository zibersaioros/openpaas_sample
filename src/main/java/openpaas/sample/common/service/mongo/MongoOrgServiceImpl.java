package openpaas.sample.common.service.mongo;

import java.util.Date;
import java.util.List;

import openpaas.sample.common.domain.mongo.MongoOrg;
import openpaas.sample.common.repository.mongo.MongoGroupRepository;
import openpaas.sample.common.repository.mongo.MongoOrgRepository;
import openpaas.sample.common.service.common.OrgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("mongoOrgService")
public class MongoOrgServiceImpl implements OrgService<MongoOrg>{

	@Autowired
	MongoOrgRepository repository;
	
	@Autowired
	MongoGroupRepository groupRepository;

	@Override
	public MongoOrg add(MongoOrg org) {
		org.setCreated(new Date());
		return repository.save(org);
	}

	@Override
	public MongoOrg modify(MongoOrg org) {
		org.setModified(new Date());
		return repository.save(org);
	}

	@Override
	public List<MongoOrg> getOrgs() {
		return repository.findAll();
	}

	@Override
	public void removeOrg(String id) {
		groupRepository.deleteByOrg_Id(id);
		repository.delete(id);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public MongoOrg getOrg(String id) {
		return repository.findOne(id);
	}
	

}
