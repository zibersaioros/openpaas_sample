package openpaas.sample.common.repository.cubrid;

import openpaas.sample.common.domain.cubrid.CubridGroup;
import openpaas.sample.common.repository.GroupRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CubridGroupRepository extends JpaRepository<CubridGroup, String>,  GroupRepository<CubridGroup>{

}