package com.zemoso.project.service;

import com.zemoso.project.model.Skill;
import com.zemoso.project.utils.CompanyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;


/**
 * Unit Test for SkillServiceImp Using "spring Mock test"
 */
@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImpTest {

    @Mock
    private SkillServiceImp skillServiceImp;

    @Test
    public void getSkill() throws Exception {

        Long id1 = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Long id2 = new Long(2);

        Skill skill1 = new Skill(id1, companyId, "JAVA");
        Skill skill2 = new Skill(id2, companyId, "Ruby");
        Mockito.when(skillServiceImp.getSkill(id1)).thenReturn(new Skill(id1, companyId, "JAVA"));
        Mockito.when(skillServiceImp.getSkill(id2)).thenReturn(new Skill(id2, companyId, "Ruby"));
        assertEquals(skill1, skillServiceImp.getSkill(id1));
        assertEquals(skill2, skillServiceImp.getSkill(id2));
    }

}