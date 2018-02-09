package com.zemoso.project.repository;

import com.zemoso.project.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
  List<Role> findAllByCompanyId(Long companyId);
}
