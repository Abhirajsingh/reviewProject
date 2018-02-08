package com.zemoso.project.utils;

import com.zemoso.project.model.Skill;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SkillMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Skill skill = new Skill(id, companyId , "Java");
        Map<String , Object> map = new HashMap<>();
        map.put(Constant.ID , id);
        map.put(Constant.NAME , "Java");
        Map<String , Object> skillMap = new SkillMapper().getObjectMap(skill);
        assertEquals(map , skillMap);

    }

}