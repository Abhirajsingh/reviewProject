package com.zemoso.project.repository;

import com.zemoso.project.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department , Long>{
    public List<Department> findAllByCompanyId(Long companyId);
}
