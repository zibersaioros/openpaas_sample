package openpaas.sample.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository<T>{

	public List<T> findByOrgId(String orgId);
	
	public T findByLabel(String label);
}