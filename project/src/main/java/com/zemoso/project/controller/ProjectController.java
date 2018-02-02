package com.zemoso.project.controller;
import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Project;
import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.service.ProjectService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);


    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeePortalService employeePortalService;

    /**
     * get the list of all project of a company;
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllProjectOfCompany(){
        Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Project> projects = projectService.getAllProject(CompanyUtil.getCompanyId());
        projects.forEach(item ->{
            Map<String , Object> departmentMap = null;
            try {
                departmentMap = projectMapper.getObjectMap(item);
            } catch (MapperException e) {
                LOGGER.error(e.getMessage() ,e);
            }
            mapList.add(departmentMap);
        }); }
        catch (DbException e){
            LOGGER.error(e.getMessage() ,e);
        }
        responseMap.put("projects" , mapList);
        return ResponseEntity.ok().body(responseMap);
    }
    /**
     * get depart of a employee;
     * @param employeeId
     * @return
     */
    @RequestMapping(path = "/{employeeId}/project",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Map<String, Object>>>
    getEmployeeLocation(@PathVariable Long employeeId){

        Project project = null;
        try {
            project = employeePortalService.getEmployee(employeeId).getProject();
        } catch (DbException e) {
            LOGGER.error(e.getMessage() ,e);
        }

        Map<String, Map<String, Object>> map = new HashMap<>();
        try {
            map.put("project", projectMapper.getObjectMap(project));
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() ,e);
        }

        return ResponseEntity.ok().body(map);

    }
}
