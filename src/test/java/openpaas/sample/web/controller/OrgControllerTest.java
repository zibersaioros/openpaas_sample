package openpaas.sample.web.controller;


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
import openpaas.sample.web.controller.OrgController;

import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, WebServletConfig.class }, initializers={SpringApplicationContextInitializer.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true, transactionManager="hsqlTransactionManager")
@Transactional
public class OrgControllerTest {
	
	private MockMvc mockMvc;
	
//	@Autowired 
//	WebApplicationContext context;
	
	@Autowired
	OrgController orgController;
	
	Gson gson;
	String dbType;
	
	@Before
	public void setup(){
		gson = new Gson();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
//		mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//				.addFilter(filter)
//				.build();
				
		mockMvc = MockMvcBuilders.standaloneSetup(orgController)
				.addFilter(filter)
				.build();
		
		dbType = ServiceType.HSQL;
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
	@Transactional
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
	
//	@Test
//	@Rollback
//	public void testAddOrgFromForm() throws Exception{
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.post("/tree")
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//				.param("label", "소프트웨어인라이프")
//				.param("desc", "soft")
//				.accept(MediaType.APPLICATION_JSON);
//		
//		mockMvc.perform(builder)
//			.andDo(print())
//			.andExpect(status().isCreated())
//			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//		
//		assertThat(orgService.count(), greaterThan(1L));
//	}
	
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
	
}
