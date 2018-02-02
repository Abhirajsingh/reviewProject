package com.zemoso.project.utils;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Skill;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SkillMapper {
    /**
     * get map from Skill object
     * @param skill
     * @return
     */
    public Map<String ,Object> getObjectMap(Skill skill) throws MapperException{
        Map<String , Object> skillMap = new HashMap<>();
        try {
        if(skill != null){
            skillMap.put(Constant.ID , skill.getId());
            skillMap.put(Constant.NAME , skill.getName());
        }
        return skillMap;}
        catch (Exception e){
            throw new MapperException("MapperException , /SkillMapper/getObjectMap", e);
        }

    }

}
