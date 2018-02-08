package com.zemoso.project.utils;

import com.zemoso.project.model.Project;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ProjectMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id1 = new Long(1);
        Long id2 = new Long(2);
        Long companyId = CompanyUtil.getCompanyId();
        Project project1 = new Project(id1 , companyId , "UAV");
        Map<String , Object> map1 = new HashMap<>();
        map1.put(Constant.ID, id1);
        map1.put(Constant.COMPANY_ID ,companyId);
        map1.put(Constant.NAME, "UAV");
        Map<String , Object> projectMap1 = new ProjectMapper().getObjectMap(project1);
        assertEquals(map1 , projectMap1);

        Project project2 = new Project(id1 , companyId , "tagCloud");
        Map<String , Object> map2 = new HashMap<>();
        map2.put(Constant.ID, id1);
        map2.put(Constant.COMPANY_ID ,companyId);
        map2.put(Constant.NAME, "tagCloud");
        Map<String , Object> projectMap2 = new ProjectMapper().getObjectMap(project2);
        assertEquals(map2 , projectMap2);

    }

}