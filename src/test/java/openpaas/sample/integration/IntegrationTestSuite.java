package openpaas.sample.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MysqlIntegrationTest.class
	, CubridIntegrationTest.class
	, MongoDBIntegrationTest.class
	, RedisIntegrationTest.class
	, GlusterFSIntegrationTest.class
	, RabbitMQIntegrationTest.class
})
public class IntegrationTestSuite {

}
