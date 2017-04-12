package openpaas.sample.common.service;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.service.common.GroupServiceDeligate;
import openpaas.sample.common.service.common.OrgServiceDeligate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class OrgServiceDeligateTest {

	@Autowired
	OrgServiceDeligate orgService;
	
	@Autowired
	GroupServiceDeligate groupService;
	
	@Before
	public void setup(){
	}

	@Test
	@Rollback
	public void testRemove() throws Exception{
		assertThat(orgService.count(ServiceType.HSQL), greaterThan(0L));
		assertThat(groupService.count(ServiceType.HSQL), greaterThan(0L));
		orgService.removeOrg("1", ServiceType.HSQL);
		assertThat(orgService.count(ServiceType.HSQL), is(0L));
		assertThat(groupService.count(ServiceType.HSQL), is(0L));
	}
	
	@Test
	public void testGetOrg() throws Exception{
		Org org = orgService.getOrg("1", ServiceType.HSQL);
		assertThat(org.getLabel(), is( "정부조직도"));
	}
	
	
	@Test
	@Rollback
	public void testAddOrg() throws Exception{
		Org org = new Org();
		org.setLabel("소프트웨어인라이프");
		org.setDesc("soft");
		org.setUrl("http://www.softwareinlife.com");
		org = orgService.add(org, ServiceType.HSQL);
		
		assertThat(org.getId(), notNullValue());
		assertThat(org.getId(), not(""));
		assertThat(org.getCreated(), is(notNullValue()));
		assertThat(orgService.count(ServiceType.HSQL), greaterThan(1L));
		
		assertThat(orgService.getOrgs(ServiceType.HSQL).size(), greaterThan(1));
	}
	
	
	@Test
	@Rollback
	public void testModifyOrg() throws Exception{
		Org org = new Org();
		org.setId("1");
		org.setLabel("소프트웨어인라이프");
		org.setDesc("soft");
		org.setUrl("http://www.softwareinlife.com");
		org = orgService.modify(org, ServiceType.HSQL);
		
		assertThat(orgService.count(ServiceType.HSQL), is(1L));
		assertThat(org.getLabel(), is("소프트웨어인라이프"));
		assertThat(org.getCreated(), is(notNullValue()));
		assertThat(org.getModified(), is(notNullValue()));
	}

}
