package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Skill;
import com.zemoso.project.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("SkillServiceImp")
public class SkillServiceImp implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    /**
     * get all skills
     * @param companyId
     * @return
     */
    public List<Skill> getAllSkill(Long companyId) throws DbException{
        try{
        return skillRepository.findAllByCompanyId(companyId);}
        catch (Exception e){
            throw new DbException("DbException , /service/SkillService/getAllSkill" ,e);
        }
    }

    /**
     * get one skill search by skillId
     * @param skillId
     * @return
     */
    public Skill getSkill(Long skillId) throws DbException{
        try{
        return skillRepository.findOne(skillId);}
        catch (Exception e){
            throw new DbException("DbException , /service/SkillService/getSkill" , e);
        }
    }
}
