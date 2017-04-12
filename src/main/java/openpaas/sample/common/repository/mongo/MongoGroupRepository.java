package openpaas.sample.common.repository.mongo;

import java.util.List;

import openpaas.sample.common.domain.mongo.MongoGroup;
import openpaas.sample.common.repository.GroupRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGroupRepository extends MongoRepository<MongoGroup, String>, GroupRepository<MongoGroup> {
	
//	@Query("{ 'orgId' : ?0 }")
//	public List<MongoGroup> findByTheMongoGroupsOrgId(String orgId);
	
	
	
//	@Query("{'label':?0}")
//	public MongoGroup findByTheMongoGroupLabel(String label);
	


/*
	public List<G> findByOrgId(Integer orgId);
	
	public G findByLabel(String label);*/
	
	List<MongoGroup> deleteByParent_Id(String id);
	Long deleteByOrg_Id(String id);
}