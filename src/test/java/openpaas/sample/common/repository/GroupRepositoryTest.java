package openpaas.sample.common.repository;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.hsql.HsqlGroup;
import openpaas.sample.common.domain.hsql.HsqlOrg;
import openpaas.sample.common.repository.hsql.HsqlGroupRepository;
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
public class GroupRepositoryTest {
	Logger logger = LoggerFactory.getLogger(GroupRepositoryTest.class);
	
	@Autowired 
	HsqlOrgRepository orgRepository;
	
	@Autowired
	HsqlGroupRepository groupRepository;
	
	@Before
	public void setup(){
	}
	
	@Test
	@Rollback
	public void testCrud() {
		
		int originalSize = groupRepository.findAll().size();
		
		
		HsqlOrg org = new HsqlOrg();
		org.setLabel("국가조직도");
		org.setUrl("http://www.naver.com"); 
		org = orgRepository.save(org);
		
		HsqlGroup group = new HsqlGroup();
		group.setLabel("국무총리");
		group.setDesc("국무총리");
		group.setUrl("http://pmo.go.kr/pmo/index.jsp");
		group.setOrg(orgRepository.findAll().get(0));
		groupRepository.save(group);
		assertThat(groupRepository.findAll().size(), is(originalSize + 1));
		
		HsqlGroup group2 = new HsqlGroup();
		group2.setLabel("국무총리밑");
		group2.setDesc("국무총리밑");
		group2.setUrl("http://pmo.go.kr/pmo/index.jsp");
		group2.setOrg(orgRepository.findAll().get(0));
		group2.setParent(group);
		groupRepository.save(group2);
		assertThat(groupRepository.findAll().size(), is(originalSize + 2));
	}
	
	@Test
	public void testPopulator() {
		assertThat(groupRepository.findAll().size(), greaterThan(10));
	}
	
	@Test
	public void testFindByLabel(){
		HsqlGroup group = groupRepository.findByLabel("국가보훈처");
		assertThat(group, not(nullValue()));
		assertThat(group.getParent(), not(nullValue()));
		assertThat(group.getParent().getLabel(), is("국무총리"));
	}
	
}
