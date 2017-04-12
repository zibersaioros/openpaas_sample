package openpaas.sample.common.service.common;

import java.util.List;

public interface OrgService<T> {

	T add(T org);
	T modify(T org);
	
	List<T> getOrgs();
	
	void removeOrg(String id);

	long count();

	T getOrg(String id);
	
}
