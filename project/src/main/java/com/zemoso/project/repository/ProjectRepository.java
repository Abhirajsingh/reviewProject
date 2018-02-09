package com.zemoso.project.repository;

import com.zemoso.project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project ,Long> {
    public List<Project> findAllByCompanyId(Long companyId);
}
