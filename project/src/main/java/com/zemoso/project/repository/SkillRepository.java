package com.zemoso.project.repository;

import com.zemoso.project.model.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill , Long>{
     List<Skill> findAllByCompanyId(Long companyId);
}
