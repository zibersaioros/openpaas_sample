package openpaas.sample.common.repository.mysql;

import openpaas.sample.common.domain.mysql.MysqlOrg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlOrgRepository extends JpaRepository<MysqlOrg, String>{
}
