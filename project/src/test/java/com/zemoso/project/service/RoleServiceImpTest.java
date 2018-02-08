package com.zemoso.project.service;

import com.zemoso.project.model.Role;
import com.zemoso.project.utils.CompanyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImpTest {

    @Mock
    private RoleServiceImp roleServiceImp;

    @Test
    public void getRole() throws Exception {

        Long id1 = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Long id2 = new Long(2);

        Role role1 = new Role(id1 , companyId , "Software Engineer");
        Role role2 = new Role(id2 , companyId , "Senior Software Enginner");
        Mockito.when(roleServiceImp.getRole(id1)).thenReturn(new Role(id1 , companyId , "Software Engineer"));
        Mockito.when(roleServiceImp.getRole(id2)).thenReturn(new Role(id2 , companyId , "Senior Software Enginner"));
        assertEquals(role1 , roleServiceImp.getRole(id1));
        assertEquals(role2 , roleServiceImp.getRole(id2));
    }

}