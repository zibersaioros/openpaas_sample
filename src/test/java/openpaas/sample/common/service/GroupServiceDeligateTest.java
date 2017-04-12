package openpaas.sample.common.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.service.common.GroupServiceDeligate;
import openpaas.sample.common.service.common.OrgServiceDeligate;
import openpaas.sample.web.controller.support.GroupDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
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
public class GroupServiceDeligateTest {

	@Autowired
	OrgServiceDeligate orgService;
	
	@Autowired
	GroupServiceDeligate groupService;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@Before
	public void setup(){
	}

	@Test
	public void testGetGroups(){
		List<Group> groups = groupService.getGroups("1", ServiceType.HSQL);
		assertThat(groups.size(), greaterThan(10));
		assertThat(groups.get(1).getParent(), notNullValue());
		assertThat(groups.get(1).getOrg(), notNullValue());
		assertThat(groups.get(1).getParent().getId(), is("1"));
	}
	
	@Test
	public void testGetGroup(){
		Group group = groupService.getGroup("3", ServiceType.HSQL);
		assertThat(group.getLabel(), is("국가안보실"));
		assertThat(group.getParent().getId(), is("1"));
	}
	
	@Test
	@Rollback
	public void testAddGroup(){
		GroupDTO dto = new GroupDTO();
		dto.setOrg_id("1");
		dto.setLabel("그룹이름");
		dto.setDesc("그룸설명");
		dto.setUrl("http://www.sample.org");
		
		Group group = groupService.add(dto, ServiceType.HSQL);
		assertThat(group.getId(), notNullValue());
		assertThat(group.getId(), not(""));
		assertThat(group.getCreated(), notNullValue());
		assertThat(group.getOrg(), notNullValue());
	}
	
	@Test
	@Rollback
	public void testModfiyGroup(){
		Group oldGroup = groupService.getGroup("20", ServiceType.HSQL);
		
		String oldLabel = oldGroup.getLabel();
		String oldParentId = oldGroup.getParent().getId();
		
		GroupDTO updateDTO = new GroupDTO();
		updateDTO.setId("20");
		updateDTO.setLabel("대통령 비서실");
		updateDTO.setUrl("http://www.naver.com");
		updateDTO.setDesc("대통령을 수행합니다");
		updateDTO.setOrg_id("1");
		updateDTO.setParent_id("1"); //대통령
		
		Group newGroup = groupService.modify(updateDTO, ServiceType.HSQL);
		 
		assertThat(oldLabel, not(newGroup.getLabel()));
		assertThat(oldParentId, not("1"));
		assertThat(newGroup.getParent().getId(), is("1"));
		assertThat(newGroup.getLabel(), is("대통령 비서실"));
		assertThat(newGroup.getCreated(), notNullValue());
		assertThat(newGroup.getModified(), notNullValue());
	}
	
	@Test
	@Rollback
	public void testRemoveGroup(){
		groupService.removeGroup("1", ServiceType.HSQL);
		assertThat(groupService.count(ServiceType.HSQL), is(0L));
	}
}
