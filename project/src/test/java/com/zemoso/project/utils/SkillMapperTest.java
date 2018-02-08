package com.zemoso.project.utils;

import com.zemoso.project.model.Skill;
import org.junit.Test;

import java.security.acl.LastOwnerException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SkillMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id1 = new Long(1);
        Long id2 = new Long(2);
        Long companyId = CompanyUtil.getCompanyId();
        Skill skill1 = new Skill(id1, companyId , "Java");
        Map<String , Object> map1 = new HashMap<>();
        map1.put(Constant.ID , id1);
        map1.put(Constant.NAME , "Java");
        Map<String , Object> skillMap1 = new SkillMapper().getObjectMap(skill1);
        assertEquals(map1 , skillMap1);

        Skill skill2 = new Skill(id2, companyId , "C++");
        Map<String , Object> map2 = new HashMap<>();
        map2.put(Constant.ID , id2);
        map2.put(Constant.NAME , "C++");
        Map<String , Object> skillMap2 = new SkillMapper().getObjectMap(skill2);
        assertEquals(map2 , skillMap2);

    }

}