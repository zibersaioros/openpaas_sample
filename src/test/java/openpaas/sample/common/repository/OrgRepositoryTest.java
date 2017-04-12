package openpaas.sample.common.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.repository.hsql.HsqlOrgRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class }, initializers={SpringApplicationContextInitializer.class})
@TransactionConfiguration(defaultRollback=true, transactionManager="hsqlTransactionManager")
@Transactional
public class OrgRepositoryTest {
	Logger logger = LoggerFactory.getLogger(OrgRepositoryTest.class);
	
	@Autowired 
	private HsqlOrgRepository orgRepository;
	
	@Before
	public void setup(){
	}
	
	@Test
	@Rollback
	public void testCrud() throws Exception{
		
		int original = orgRepository.findAll().size();
		
		HsqlOrg org = new HsqlOrg();
		org.setLabel("국가조직도");
		org.setUrl("http://www.naver.com"); 
		
		HsqlOrg saved = orgRepository.save(org);
		
		assertThat(orgRepository.findAll().size(), is(original + 1));
		assertThat(saved.getId(), notNullValue() );
		assertThat(saved.getId(), not("") );
		assertThat(saved.getCreated(), notNullValue());
		
	}
	
	@Test
	public void testPopulate() throws Exception{
		assertThat(orgRepository.findAll().get(0).getId(), is("1"));
		assertThat(orgRepository.findAll().size(), greaterThan(0));
	}

//	@Test
//	public void testFindByUrl(){
//		Org org  = orgRepository.findByUrl("gov");
//		assertThat(tree, notNullValue());
//		
//		List<Group> orgs = tree.getOrgs();
//		assertThat(orgs.size(), greaterThan(0));
//	}
}
