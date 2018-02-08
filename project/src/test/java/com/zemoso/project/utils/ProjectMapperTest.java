package com.zemoso.project.utils;

import com.zemoso.project.model.Project;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ProjectMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Project project = new Project(id , companyId , "UAV");
        Map<String , Object> map = new HashMap<>();
        map.put(Constant.ID, id);
        map.put(Constant.COMPANY_ID ,companyId);
        map.put(Constant.NAME, "UAV");
        Map<String , Object> projectMap = new ProjectMapper().getObjectMap(project);
        assertEquals(map , projectMap);
    }

}