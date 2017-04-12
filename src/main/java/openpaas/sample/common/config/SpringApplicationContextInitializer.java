package openpaas.sample.common.config;

import java.util.ArrayList;
import java.util.List;

import openpaas.sample.common.config.service.ServiceConst.ServiceType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

public class SpringApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Log logger = LogFactory.getLog(SpringApplicationContextInitializer.class);


    public static final String IN_MEMORY_PROFILE = "in-memory";


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment appEnvironment = applicationContext.getEnvironment();

        String[] persistenceProfiles = getCloudServicesId();
        if (persistenceProfiles == null) {
            persistenceProfiles = new String[] { IN_MEMORY_PROFILE };
        }

        //서비스 아이디로 프로필 활성화
        for (String persistenceProfile : persistenceProfiles){
        	if(persistenceProfile.toLowerCase().contains("mysql"))
        		appEnvironment.addActiveProfile(ServiceType.MYSQL);
        	else if(persistenceProfile.toLowerCase().contains("cubrid"))
        		appEnvironment.addActiveProfile(ServiceType.CUBRID);
        	else if(persistenceProfile.toLowerCase().contains("mongo"))
        		appEnvironment.addActiveProfile(ServiceType.MONGODB);
        	else if(persistenceProfile.toLowerCase().contains("redis"))
        		appEnvironment.addActiveProfile(ServiceType.REDIS);
        	else if(persistenceProfile.toLowerCase().contains("rabbit"))
        		appEnvironment.addActiveProfile(ServiceType.RABBITMQ);
        	else if(persistenceProfile.toLowerCase().contains("gluster"))
        		appEnvironment.addActiveProfile(ServiceType.GLUSTERFS);
        		
//            appEnvironment.addActiveProfile(persistenceProfile);
        }
    }

    /**
     * 바인딩된 서비스들의 아이디배열을  반환
     * @return
     */
    public String[] getCloudServicesId() {
    	Cloud cloud = getCloud();
    	
        if (cloud == null) {
            return null;
        }
        
        List<String> ids = new ArrayList<String>();
        //바인딩된 서비스 정보를 가져옴.
        List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
        logger.info("Found serviceInfos: " + StringUtils.collectionToCommaDelimitedString(serviceInfos));

        //바인딩된 서비스의 아이디를 id리스트에 저장.
        for (ServiceInfo serviceInfo : serviceInfos)
        	ids.add(serviceInfo.getId());
        
        if (ids.size() > 0)
        	return ids.toArray(new String[ids.size()]);

        return null;
    }

    /**
     * 현재 환경에 적합한 클라우드 객체를 반환.
     * @return
     */
    private Cloud getCloud() {
        try {
            CloudFactory cloudFactory = new CloudFactory();
            return cloudFactory.getCloud();
        } catch (CloudException ce) {
            return null;
        }
    }


}
