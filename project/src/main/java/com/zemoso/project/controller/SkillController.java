package com.zemoso.project.controller;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Skill;
import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.service.SkillService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.SkillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/skills")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private EmployeePortalService employeePortalService;

    /**
     * get all skills
     *
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllSkill() {
        Map<String, List<Map<String, Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Skill> skills = skillService.getAllSkill(CompanyUtil.getCompanyId());
            skills.forEach(skill -> {
                Map<String, Object> skillMap = null;
                try {
                    skillMap = skillMapper.getObjectMap(skill);
                } catch (MapperException e) {
                    log.error(e.getMessage(), e);
                }
                mapList.add(skillMap);
            });
            responseMap.put("skills", mapList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            log.error("Skills is null", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * get skills of a employee;
     *
     * @param employeeId
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(path = "/{employeeId}/skills", method = RequestMethod.GET)
    public ResponseEntity getEmployeeSkills(@PathVariable Long employeeId) {
        Map<String, List<Map<String, Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            Set<Skill> skillSet = employeePortalService.getEmployee(employeeId).getSkill();
            skillSet.forEach(skill -> {
                Map<String, Object> skillMap = null;
                try {
                    skillMap = skillMapper.getObjectMap(skill);
                } catch (MapperException e) {
                    log.error(e.getMessage(), e);
                }
                mapList.add(skillMap);
            });
            responseMap.put("skills", mapList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
