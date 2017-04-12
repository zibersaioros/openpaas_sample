package openpaas.sample.common.repository.hsql;

import openpaas.sample.common.domain.hsql.HsqlOrg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HsqlOrgRepository extends JpaRepository<HsqlOrg, String>{
}
