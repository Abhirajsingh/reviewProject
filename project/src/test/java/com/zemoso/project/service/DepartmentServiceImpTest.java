package com.zemoso.project.service;

import com.zemoso.project.model.Department;
import com.zemoso.project.utils.CompanyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Unit Test for DeparmentServiceImp Using "spring Mock test"
 */
@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImpTest {

    @Mock
    private DepartmentServiceImp departmentServiceImp;

    @Test
    public void getDepartment() throws Exception {
      Long id1 = new Long(1);
      Long companyId = CompanyUtil.getCompanyId();
      Long id2 = new Long(2);

        Department  department2 = new Department("Testing" , companyId , id2);
        Department department1 = new Department("Development" , companyId , id1);
        Mockito.when(departmentServiceImp.getDepartment(id1)).thenReturn(new Department("Development" , companyId , id1));
        Mockito.when(departmentServiceImp.getDepartment(id2)).thenReturn(new Department("Testing" , companyId , id2));
        assertEquals(department1 , departmentServiceImp.getDepartment(id1));
        assertEquals(department2 , departmentServiceImp.getDepartment(id2));

    }

}