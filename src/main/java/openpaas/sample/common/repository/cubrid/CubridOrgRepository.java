package openpaas.sample.common.repository.cubrid;

import openpaas.sample.common.domain.cubrid.CubridOrg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CubridOrgRepository extends JpaRepository<CubridOrg, String>{
}
