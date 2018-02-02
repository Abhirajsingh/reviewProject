package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {

    /**
     * get all skills
     * @param companyId
     * @return
     */
    List<Skill> getAllSkill(Long companyId) throws DbException;
    /**
     * get one skill search by skillId
     * @param skillId
     * @return
     */
    Skill getSkill(Long skillId) throws DbException;
}
