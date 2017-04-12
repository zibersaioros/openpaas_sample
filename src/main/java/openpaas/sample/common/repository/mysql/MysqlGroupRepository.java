package openpaas.sample.common.repository.mysql;

import openpaas.sample.common.domain.mysql.MysqlGroup;
import openpaas.sample.common.repository.GroupRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlGroupRepository extends JpaRepository<MysqlGroup, String>,  GroupRepository<MysqlGroup>{

}