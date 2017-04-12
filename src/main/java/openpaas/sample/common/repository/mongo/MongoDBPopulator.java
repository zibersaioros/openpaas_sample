package openpaas.sample.common.repository.mongo;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.Org;
import openpaas.sample.common.service.common.GroupServiceDeligate;
import openpaas.sample.common.service.common.OrgServiceDeligate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Component
public class MongoDBPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware{
    
    Logger logger = LoggerFactory.getLogger(MongoDBPopulator.class);

    private ApplicationContext applicationContext;
    
    @Autowired
    OrgServiceDeligate orgService;
    @Autowired
    GroupServiceDeligate groupService;
    
    @Autowired
    MongoOrgRepository orgRepository;
    @Autowired
    MongoGroupRepository groupRepository;
    
    private ClassPathResource orgResource, groupResource;
    
    @Autowired
    private Gson gson;
    
    public MongoDBPopulator() {
        orgResource = new ClassPathResource("org.json");
        groupResource = new ClassPathResource("group.json");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().equals(applicationContext)){
        	groupRepository.deleteAll();
        	orgRepository.deleteAll();
        	
            try {
                //조직도 삽입
                JsonReader jr = new JsonReader(new FileReader(orgResource.getFile()));//new InputStreamReader(new FileInputStream(orgResource.getFile()), "UTF-8"));
                Type collectionType = new TypeToken<List<Org>>(){}.getType();
                List<Org> orgList = gson.fromJson(jr, collectionType);
                Org managedOrg = orgService.add(orgList.get(0), ServiceType.MONGODB);
                jr.close();
                
                //그룹 삽입
                jr = new JsonReader(new FileReader(groupResource.getFile()));//new InputStreamReader(new FileInputStream(groupResource.getFile()), "UTF-8"));
                Type groupType = new TypeToken<List<Group>>(){}.getType();
                List<Group> groupList = gson.fromJson(jr, groupType);
                for(Group group : groupList){
                    groupService.saveInInitPopulator(group, managedOrg.getId(), ServiceType.MONGODB);
                }
                jr.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }

}
