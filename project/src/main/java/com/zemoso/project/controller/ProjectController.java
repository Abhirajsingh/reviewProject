package com.zemoso.project.controller;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Project;
import com.zemoso.project.service.ProjectService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/projects")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * get the list of all project of a company;
     *
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllProjectOfCompany() {
        Map<String, List<Map<String, Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Project> projects = projectService.getAllProject(CompanyUtil.getCompanyId());
            projects.forEach(item -> {
                Map<String, Object> departmentMap = null;
                try {
                    departmentMap = projectMapper.getObjectMap(item);
                } catch (MapperException e) {
                    log.error(e.getMessage(), e);
                }
                mapList.add(departmentMap);

            });
            responseMap.put("projects", mapList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (DbException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * get depart of a employee;
     *
     * @param projectId
     * @return <Map<String, Map<String, Object>>>
     */
    @RequestMapping(path = "/project/{projectId}", method = RequestMethod.GET)
    public ResponseEntity getEmployeeLocation(@PathVariable Long projectId) {
        try {
            Project project = projectService.getProject(projectId);
            Map<String, Map<String, Object>> map = new HashMap<>();
            map.put("project", projectMapper.getObjectMap(project));
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }
}
