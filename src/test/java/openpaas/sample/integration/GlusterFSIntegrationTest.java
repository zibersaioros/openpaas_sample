package openpaas.sample.integration;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.web.config.WebServletConfig;

import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, WebServletConfig.class }, initializers={SpringApplicationContextInitializer.class})
@WebAppConfiguration
public class GlusterFSIntegrationTest {

	MockMvc mockMvc;

	ClassPathResource testImageResource;

	@Autowired 
	WebApplicationContext wac;

	//	@Autowired
	//	FileController controller;
	
	Gson gson;
	
	@Autowired
	Account account;

	@Before
	public void setup(){
		gson = new Gson();

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);

		//		mockMvc = MockMvcBuilders.standaloneSetup(controller)
		//			.addFilter(filter)
		//			.build();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(filter).build();

		testImageResource = new ClassPathResource("test.png");
		
	}


	@Test
	public void uploadFile() throws Exception{

		MockMultipartFile file = new MockMultipartFile("file", testImageResource.getFilename(), "image/png", testImageResource.getInputStream());

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/upload")
				.file(file)
				.accept(MediaType.APPLICATION_JSON);
		
		String content = mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.thumb_img_path", notNullValue()))
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		JsonObject jsonObj = gson.fromJson(content, JsonElement.class).getAsJsonObject();
		String thumb_img_path = jsonObj.get("thumb_img_path").getAsString();
		
		//삭제
		Container container = account.getContainer("images");
		assertThat(container.exists(), is(true));
		StoredObject object = container.getObject(thumb_img_path.substring(thumb_img_path.lastIndexOf("/")));
		assertThat(object.exists(), is(true));
		object.delete();
		assertThat(object.exists(), is(false));
	}
}
