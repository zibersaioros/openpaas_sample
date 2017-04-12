package openpaas.sample.common.repository.hsql;

import org.springframework.data.jpa.repository.JpaRepository;

import openpaas.sample.common.domain.hsql.HsqlGroup;
import openpaas.sample.common.repository.GroupRepository;

public interface HsqlGroupRepository extends JpaRepository<HsqlGroup, String>,  GroupRepository<HsqlGroup>{

}