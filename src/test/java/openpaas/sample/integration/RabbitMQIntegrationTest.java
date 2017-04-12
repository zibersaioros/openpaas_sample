package openpaas.sample.integration;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.config.service.ServiceConst.State;
import openpaas.sample.common.domain.Org;
import openpaas.sample.web.config.WebServletConfig;
import openpaas.sample.web.controller.OrgController;
import openpaas.sample.web.controller.support.GroupDTO;

import org.hsqldb.StatementTypes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, WebServletConfig.class }, initializers={SpringApplicationContextInitializer.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true, transactionManager="mysqlTransactionManager")
@Transactional
public class RabbitMQIntegrationTest {

	private MockMvc mockMvc;

	@Autowired 
	WebApplicationContext context;

	//	@Autowired
	//	OrgController orgController;

	Gson gson;
	String dbType;
	
	MockHttpServletRequestBuilder statusBuilder;

	@Before
	public void setup(){
		gson = new Gson();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilter(filter)
				.build();

		//		mockMvc = MockMvcBuilders.standaloneSetup(orgController)
		//				.addFilter(filter)
		//				.build();

		dbType = ServiceType.MYSQL;
		
		statusBuilder = MockMvcRequestBuilders
				.get("/org-chart/1/status/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
	}

	@Test
	public void testNoChanges() throws Exception{
		//큐 초기화 
		mockMvc.perform(statusBuilder);
		
		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.NO_CHANGES)));
	}


	@Test
	@Transactional
	@Rollback
	public void testOrgUpdated() throws Exception{
		//큐 초기화
		mockMvc.perform(statusBuilder);
		
		Org org = new Org();
		org.setLabel("소프트웨어인라이프");
		org.setDesc("soft");
		org.setUrl("http://www.softwareinlife.com");

		String content = gson.toJson(org);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/orgs/1/" + dbType)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is("1")))
				.andExpect(jsonPath("$.modified", is(not(""))));


		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.ORG_UPDATED)));

	}

	@Test
	@Transactional
	@Rollback
	public void testOrgDeleted() throws Exception{
		//큐 초기화
		mockMvc.perform(statusBuilder);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/orgs/1/" + dbType);

		mockMvc.perform(builder)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is("1")));

		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.ORG_DELETED)));
	}

	@Test
	@Transactional
	@Rollback
	public void testGroupAdded() throws Exception{
		//큐 초기화
		mockMvc.perform(statusBuilder);
		
		GroupDTO createDTO =  new GroupDTO();
		createDTO.setOrg_id("1");
		createDTO.setLabel("대통령 비서실");
		createDTO.setUrl("http://www.naver.com");
		createDTO.setDesc("대통령을 수행합니다");
		createDTO.setThumb_img_name("test.jpg");
		createDTO.setParent_id("1"); //대통령

		String content = gson.toJson(createDTO);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/orgs/1/groups/" + dbType)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", not("")))
				.andExpect(jsonPath("$.org_id",  is("1")))
				.andExpect(jsonPath("$.created", not("")));


		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.GROUP_ADDED)));
	}

	@Test
	@Transactional
	@Rollback
	public void testGroupUpdated() throws Exception{
		//큐 초기화
		mockMvc.perform(statusBuilder);
		
		GroupDTO updateDTO = new GroupDTO();
		updateDTO.setLabel("기재부 대리");
		updateDTO.setUrl("http://www.naver.com");
		updateDTO.setDesc("대통령 직속 기재부");
		updateDTO.setOrg_id("1");
		updateDTO.setParent_id("1"); //대통령

		String content = gson.toJson(updateDTO);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/orgs/1/groups/20/" + dbType)
				.contentType(MediaType.APPLICATION_JSON)
				//				 .contentType(MediaType.TEXT_PLAIN)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is("20")))
				.andExpect(jsonPath("$.label", is("기재부 대리")))
				.andExpect(jsonPath("$.parent_id", is("1")))
				.andExpect(jsonPath("$.modified", not("")))
				.andExpect(jsonPath("$.created", not("")));
		
		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.GROUP_UPDATED)));
	}

	@Test
	@Transactional
	@Rollback
	public void testRemoveGroup() throws Exception{
		//큐 초기화
		mockMvc.perform(statusBuilder);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/orgs/1/groups/1/" + dbType);
		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("1")));
		
		mockMvc.perform(statusBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status", is(State.GROUP_DELETED)));

	}

}
