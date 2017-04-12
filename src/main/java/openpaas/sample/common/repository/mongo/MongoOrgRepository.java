package openpaas.sample.common.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import openpaas.sample.common.domain.Org;
import openpaas.sample.common.domain.mongo.MongoOrg;

//@Repository("mongoRepository")
public interface MongoOrgRepository extends MongoRepository<MongoOrg, String> {

}