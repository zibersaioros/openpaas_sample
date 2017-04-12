package openpaas.sample.common.config;

import openpaas.sample.common.config.service.CloudMysqlConfig;
import openpaas.sample.common.domain.Group;
import openpaas.sample.common.domain.cubrid.CubridGroup;
import openpaas.sample.common.domain.hsql.HsqlGroup;
import openpaas.sample.common.domain.mongo.MongoGroup;
import openpaas.sample.common.domain.mysql.MysqlGroup;
import openpaas.sample.common.service.common.OrgService;
import openpaas.sample.web.controller.support.GroupDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.gson.Gson;

@Configuration
@ComponentScan(basePackageClasses={CloudMysqlConfig.class,  OrgService.class})
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.addMappings(new PropertyMap<Group, GroupDTO.Response>() {
			@Override
			protected void configure() {
				map().setOrg_id(source.getOrg().getId());
				map().setParent_id(source.getParent().getId());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<GroupDTO, HsqlGroup>() {
			@Override
			protected void configure() {
				skip().setCreated(null);
				skip().setModified(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<GroupDTO, MysqlGroup>() {
			@Override
			protected void configure() {
				skip().setCreated(null);
				skip().setModified(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<GroupDTO, CubridGroup>() {
			@Override
			protected void configure() {
				skip().setCreated(null);
				skip().setModified(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<GroupDTO, MongoGroup>() {
			@Override
			protected void configure() {
				skip().setCreated(null);
				skip().setModified(null);
			}
		});
		
		return modelMapper;
	}
	
	@Bean
	public Gson gson(){
		return new Gson();
	}
}
