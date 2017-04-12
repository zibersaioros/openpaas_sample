package openpaas.sample.common.service.common;

import java.util.List;

import org.javaswift.joss.model.Account;

import openpaas.sample.web.controller.support.GroupDTO;

public interface GroupService<T> {

	T add(GroupDTO groupDTO);
	T modify(GroupDTO groupDTO);
	
	T saveInInitPopulator(T group, String orgId);
	
	List<T> getGroups(String orgId);
	
	T getGroup(String id);
	
	void removeGroup(String id);
	
	long count();
	
	T findByLabel(String label);
	
	/**
	 * 기존 이미지를 삭제한다.
	 * @param account Swift Root Entity
	 * @param oldFullPath
	 * @param newFullPath
	 */
	void deleteOldImage(Account account, String oldFullPath, String newFullPath);
}
