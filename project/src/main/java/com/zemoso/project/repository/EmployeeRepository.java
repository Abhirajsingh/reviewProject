package com.zemoso.project.repository;

import com.zemoso.project.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    public List<Employee> findAllByCompanyIdOrderByLastUpdatedDesc(Long companyId);
}
