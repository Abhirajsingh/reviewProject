package com.zemoso.project.utils;

import com.zemoso.project.model.Role;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit Test for RoleMapper Class
 */
public class RoleMapperTest {
    /**
     * test for getObjectMap method of RoleMapper Class;
     * @throws Exception
     */
    @Test
    public void getObjectMap() throws Exception {
        Long id1 = new Long(1);
        Long id2 = new Long(2);
        Long companyId = CompanyUtil.getCompanyId();
        Role role = new Role(id1 ,companyId , "Software Engineer");
        Map<String ,Object> map = new HashMap<>();
        map.put(Constant.ID , id1);
        map.put(Constant.NAME , "Software Engineer");
        Map<String , Object> roleMap = new RoleMapper().getObjectMap(role);
        assertEquals(map , roleMap);

        Role role2 = new Role(id2 ,companyId , "Senior Software Engineer");
        Map<String ,Object> map2 = new HashMap<>();
        map2.put(Constant.ID , id2);
        map2.put(Constant.NAME , "Senior Software Engineer");
        Map<String , Object> roleMap2 = new RoleMapper().getObjectMap(role2);
        assertEquals(map2 , roleMap2);
    }

}