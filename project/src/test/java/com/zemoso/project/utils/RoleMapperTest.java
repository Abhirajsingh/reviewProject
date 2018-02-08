package com.zemoso.project.utils;

import com.zemoso.project.model.Role;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RoleMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Role role = new Role(id ,companyId , "Software Engineer");
        Map<String ,Object> map = new HashMap<>();
        map.put(Constant.ID , id);
        map.put(Constant.NAME , "Software Engineer");
        Map<String , Object> roleMap = new RoleMapper().getObjectMap(role);
        assertEquals(map , roleMap);
    }

}