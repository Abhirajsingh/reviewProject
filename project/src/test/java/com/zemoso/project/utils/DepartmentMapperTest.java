package com.zemoso.project.utils;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.zemoso.project.model.Department;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DepartmentMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Department department = new Department("Testing" , companyId , id);
        Map<String , Object> map = new HashMap<>();
        map.put(Constant.ID,id);
        map.put(Constant.COMPANY_ID, companyId);
        map.put(Constant.NAME, "Testing");
        Map<String , Object> departmentMap = new DepartmentMapper().getObjectMap(department);
        assertEquals(map, departmentMap);

    }

}