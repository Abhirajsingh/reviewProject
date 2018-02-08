package com.zemoso.project.utils;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.zemoso.project.model.Department;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * unit test for the DepartMapper
 */
public class DepartmentMapperTest {

    /**
     * test the getObjectMap of DepartmentMapper
     * @throws Exception
     */
    @Test
    public void getObjectMap() throws Exception {
        Long id1 = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Long id2 = new Long(2);
        Department department1 = new Department("Testing" , companyId , id1);
        Map<String , Object> map1 = new HashMap<>();
        map1.put(Constant.ID,id1);
        map1.put(Constant.COMPANY_ID, companyId);
        map1.put(Constant.NAME, "Testing");
        Map<String , Object> departmentMap1 = new DepartmentMapper().getObjectMap(department1);
        assertEquals(map1, departmentMap1);
        Department department2 = new Department("Development" , companyId , id2);
        Map<String , Object> map2 = new HashMap<>();
        map2.put(Constant.ID , id2);
        map2.put(Constant.COMPANY_ID , companyId);
        map2.put(Constant.NAME, "Development");
        Map<String, Object> departmentMap2 = new DepartmentMapper().getObjectMap(department2);
        assertEquals(map2 , departmentMap2);

    }

}