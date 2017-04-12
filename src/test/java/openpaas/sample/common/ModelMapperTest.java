package openpaas.sample.common;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import openpaas.sample.common.config.AppConfig;
import openpaas.sample.common.config.SpringApplicationContextInitializer;
import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.domain.mongo.MongoOrg;
import openpaas.sample.common.service.common.OrgServiceDeligate;
import openpaas.sample.web.controller.support.OrgDTO;
import openpaas.sample.web.controller.support.OrgDTO.Response;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}, initializers={SpringApplicationContextInitializer.class})
public class ModelMapperTest {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	OrgServiceDeligate orgService;
	
	@Autowired
	Gson gson;
	
	@Before
	public void setup(){
	}
	
	@Test
	@Ignore
	public void mappingTest() {
		Org org = orgService.getOrg("1", ServiceType.HSQL);
		assertThat(org.getModified(), nullValue());
		Response response = modelMapper.map(org, OrgDTO.Response.class);
		assertThat(response.getModified(), is(""));
		
		MongoOrg mongoOrg = modelMapper.map(org, MongoOrg.class);
		assertThat(mongoOrg.getId(), notNullValue());
		System.out.println("ohdoking : " + mongoOrg.getId());
		
	}
	
	@Test
//	@Ignore
	public void printJson() throws Exception{
		ClassPathResource groupResource = new ClassPathResource("group.json");
        //그룹 삽입
        JsonReader jr = new JsonReader(new FileReader(groupResource.getFile()));//new InputStreamReader(new FileInputStream(groupResource.getFile()), "UTF-8"));
        Type groupType = new TypeToken<List<Group>>(){}.getType();
        List<Group> groupList = gson.fromJson(jr, groupType);
        String prevSuperior = "";
        for(Group group : groupList){
        	
        	System.out.println("id = ObjectId()");
        	System.out.println("db.Groups.insert({");
        	System.out.println("\t_id : id,");
        	System.out.println("\torgId : db.Orgs.findOne({label:\"정부조직도\"})._id,");
        	if(group.getSuperior() != null && !group.getSuperior().equals(""))
        		System.out.println("\tparentId : db.Groups.findOne({label:\""+ group.getSuperior()+"\"})._id,");
        	else
        		System.out.println("\tparentId : null,");
        	System.out.println("\tlabel : \"" + group.getLabel() + "\",");
        	System.out.println("\tdesc : \"\",");
        	System.out.println("\tthumbImgPath : \"" + group.getThumb_img_path()+ "\",");
        	System.out.println("\tthumbImgName : \"\",");
        	System.out.println("\turl : \"" + group.getUrl()+"\",");
        	System.out.println("\tcreated : id.getTimestamp(),");
        	System.out.println("\tmodified : null");
        	/*
        	System.out.println("\torg : DBRef(\"Orgs\", db.Orgs.findOne({label:\"정부조직도\"})._id)");
        	if(group.getSuperior() != null && !group.getSuperior().equals(""))
        		System.out.println("\t,parent : DBRef(\"Groups\", db.Groups.findOne({label:\""+ group.getSuperior()+"\"})._id)");
        		*/
        	System.out.println("})");
        	System.out.println();
        }
        jr.close();
	}
	

}
