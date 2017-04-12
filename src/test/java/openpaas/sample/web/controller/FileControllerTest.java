package openpaas.sample.web.controller;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.web.config.WebServletConfig;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, WebServletConfig.class }, initializers={SpringApplicationContextInitializer.class})
@WebAppConfiguration
public class FileControllerTest {

	MockMvc mockMvc;

	ClassPathResource testImageResource;

	@Autowired 
	WebApplicationContext wac;

	//	@Autowired
	//	FileController controller;

	@Before
	public void setup(){

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
	public void uploadTest() throws Exception{

		MockMultipartFile file = new MockMultipartFile("file", testImageResource.getFilename(), "image/png", testImageResource.getInputStream());

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/upload")
				.file(file)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder)
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.error", is("No GlusterFS")));

	}
}
