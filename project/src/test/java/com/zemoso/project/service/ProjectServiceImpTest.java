package com.zemoso.project.service;

import com.zemoso.project.model.Project;
import com.zemoso.project.utils.CompanyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImpTest {

    @Mock
    private ProjectServiceImp projectServiceImp;

    @Test
    public void getProject() throws Exception {
        Long id1 = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Long id2 = new Long(2);
        Project project1 = new Project(id1 , companyId , "UAV");
        Project project2 = new Project(id2 , companyId , "tagCloud");
        Mockito.when(projectServiceImp.getProject(id1)).thenReturn(new Project(id1 , companyId , "UAV"));
        Mockito.when(projectServiceImp.getProject(id2)).thenReturn(new Project(id2 , companyId , "tagCloud"));
        assertEquals(project1 , projectServiceImp.getProject(id1));
        assertEquals(project2 , projectServiceImp.getProject(id2));

    }

}