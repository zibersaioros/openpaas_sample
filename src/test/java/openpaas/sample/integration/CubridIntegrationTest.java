package openpaas.sample.integration;


import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.Org;
import openpaas.sample.web.config.WebServletConfig;
import openpaas.sample.web.controller.support.GroupDTO;

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
@TransactionConfiguration(defaultRollback=true, transactionManager="cubridTransactionManager")
@Transactional
public class CubridIntegrationTest {
	
	private MockMvc mockMvc;
	
	@Autowired 
	WebApplicationContext context;
	
//	@Autowired
//	OrgController orgController;
	
	Gson gson;
	String dbType;
	
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
		
		dbType = ServiceType.CUBRID;
	}
	
	@Test
	public void testGetOrgChart() throws Exception{
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/org-chart/1/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.org.label", is("정부조직도")))
			.andExpect(jsonPath("$.groups[0].label", is("대통령")))
			.andExpect(jsonPath("$.groups[0].org_id", is("1")))
			.andExpect(jsonPath("$.groups[0].parent_id", is("")))
			.andExpect(jsonPath("$.groups[1].parent_id", is("1")))
			.andExpect(jsonPath("$.groups[1].modified", is("")));
	}
	
	@Test
	public void testGetOrgs() throws Exception{
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/orgs/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("$.orgs[0].label", is("정부조직도")));
			.andExpect(jsonPath("$.orgs[0].label", anyOf(is("정부조직도"), is("소프트웨어인라이프"))));
	}
	
	@Test
	public void testGetOrg() throws Exception{
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/orgs/1/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.org.label", anyOf(is("정부조직도"), is("소프트웨어인라이프"))));
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testAddOrg() throws Exception{
		Org org = new Org();
		org.setLabel("소프트웨어인라이프");
		org.setDesc("soft");
		org.setUrl("http://www.softwareinlife.com");
		
		String content = gson.toJson(org);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/orgs/" + dbType)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is("2")));
		
//		assertThat(orgService.count(), greaterThan(1L));
	}
	
	
	@Test
	@Rollback
	public void testModifyOrg() throws Exception{
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
		
	}
	
	
	@Test
	public void testAddOrgException() throws Exception{
		Org org = new Org();
		org.setDesc("소프트웨어인라이프");
		
		String content = gson.toJson(org);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/orgs/" + dbType)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testRemoveOrg() throws Exception{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/orgs/1/" + dbType);
		
		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("1")));
	}
	
	@Test
	public void testGetGroups() throws Exception{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/orgs/1/groups/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.groups[0].label", is("대통령")))
			.andExpect(jsonPath("$.groups[0].id", is("1")))
			.andExpect(jsonPath("$.groups[1].label", is("대통령비서실")))
			.andExpect(jsonPath("$.groups[1].parent_id", is("1")));
	}
	
	@Test
	public void testGetGroup() throws Exception{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/orgs/1/groups/2/" + dbType)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.group.label", is("대통령비서실")))
			.andExpect(jsonPath("$.group.org_id", is("1")))
			.andExpect(jsonPath("$.group.parent_id", is("1")));
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testAddGroup() throws Exception{
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
//				 .contentType(MediaType.TEXT_PLAIN)
				 .content(content)
				 .accept(MediaType.APPLICATION_JSON);
		 
		 mockMvc.perform(builder)
		 		.andDo(print())
		 		.andExpect(status().isOk())
		 		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id", not("")))
		 		.andExpect(jsonPath("$.org_id",  is("1")))
		 		.andExpect(jsonPath("$.created", not("")));
		 
		 ///////////////////////////////////////////////////////
		 
		 GroupDTO createDTO2 =  new GroupDTO();
		 createDTO2.setOrg_id("1");
		 createDTO2.setLabel("2대통령 비서실");
		 createDTO2.setUrl("http://www.naver.com");
		 createDTO2.setDesc("대통령이 2명");
		 createDTO2.setThumb_img_name("test.jpg");
		 
		 content = gson.toJson(createDTO2);
		 
		 builder = MockMvcRequestBuilders.post("/orgs/1/groups/" + dbType)
				 .contentType(MediaType.APPLICATION_JSON)
//				 .contentType(MediaType.TEXT_PLAIN)
				 .content(content)
				 .accept(MediaType.APPLICATION_JSON);
		 
		 mockMvc.perform(builder)
		 		.andDo(print())
		 		.andExpect(status().isOk())
		 		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id", not("")))
		 		.andExpect(jsonPath("$.org_id",  is("1")))
		 		.andExpect(jsonPath("$.created", not("")));
	}
	
	@Test
	@Rollback
	 public void testModifyGroup() throws Exception{
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
	 }
	
	@Test
	@Transactional
	@Rollback
	public void testRemoveGroup() throws Exception{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/orgs/1/groups/1/" + dbType);
		
		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("1")));
		
	}
	
}
