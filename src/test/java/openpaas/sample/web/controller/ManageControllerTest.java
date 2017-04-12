package openpaas.sample.web.controller;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.web.config.WebServletConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, WebServletConfig.class }, initializers={SpringApplicationContextInitializer.class})
@WebAppConfiguration
public class ManageControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired 
	WebApplicationContext wac;
	
	Gson gson;
	
	@Before
	public void setup(){
		gson = new Gson();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(filter).build();
		
	}
	
	@Test
	public void testValidLogin() throws Exception{
		Map<String, String> body = new HashMap<String, String>();
		body.put("id", "admin");
		body.put("password", "admin");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/manage/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(body))
				.accept(MediaType.APPLICATION_JSON);
		
		HttpSession session = mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn()
			.getRequest()
			.getSession();
		
		assertThat(session, notNullValue());
		assertThat(session.getAttribute("idKey"), notNullValue());
		assertThat(session.getAttribute("passwordKey"), notNullValue());
	}
	
	@Test
	public void testInvalidLogin() throws Exception{
		Map<String, String> body = new HashMap<String, String>();
		body.put("id", "inavlidId");
		body.put("password", "invalidPassword");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/manage/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(body))
				.accept(MediaType.APPLICATION_JSON);
		
		HttpSession session = mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isInternalServerError())
			.andReturn()
			.getRequest()
			.getSession();
		
		assertThat(session, notNullValue());
		assertThat(session.getAttribute("idKey"), nullValue());
		assertThat(session.getAttribute("passwordKey"), nullValue());
	}
	
	
	@Test
	public void testLogout() throws Exception{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/manage/logout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		HttpSession session = mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn()
			.getRequest()
			.getSession();
		
		assertThat(session, notNullValue());
		assertThat(session.getAttribute("idKey"), nullValue());
		assertThat(session.getAttribute("passwordKey"), nullValue());
	}
	
	@Test
	public void testFowardToManage() throws Exception{
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("idKey", "admin");
		session.put("passwordKey", "admin");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/manage")
				.sessionAttrs(session);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(forwardedUrlPattern("/**/manage.*"));
	}
	
	@Test
	public void testRedirectToLogin() throws Exception{
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("idKey", "invalid");
		session.put("passwordKey", "invalid");
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/manage")
				.sessionAttrs(session);
		
		mockMvc.perform(builder)
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login"));
	}
}
