package com.zemoso.project.controller;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Skill;
import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.service.SkillService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.SkillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);


    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private EmployeePortalService employeePortalService;

    /**
     * get all skills
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity getAllSkill(){
        Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Skill> skills = skillService.getAllSkill(CompanyUtil.getCompanyId());
            skills.forEach(skill -> {
                Map<String, Object> skillMap = null;
                try {
                    skillMap = skillMapper.getObjectMap(skill);
                } catch (MapperException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                mapList.add(skillMap);
            });
            responseMap.put("skills" , mapList);
            return ResponseEntity.ok().body(responseMap);
        }catch (Exception e){
            LOGGER.error("Skills is null",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }
    /**
     * get skills of a employee;
     * @param employeeId
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(path = "/{employeeId}/skills",method = RequestMethod.GET)
    public ResponseEntity getEmployeeSkills(@PathVariable Long employeeId){
        Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            Set<Skill> skillSet = employeePortalService.getEmployee(employeeId).getSkill();
            skillSet.forEach(skill -> {
                Map<String, Object> skillMap = null;
                try {
                    skillMap = skillMapper.getObjectMap(skill);
                } catch (MapperException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                mapList.add(skillMap);
            });
            responseMap.put("skills", mapList);
            return ResponseEntity.ok().body(responseMap);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage() ,e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
