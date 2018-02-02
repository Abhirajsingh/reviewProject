package com.zemoso.project.controller;


import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Department;
import com.zemoso.project.service.DepartmentService;
import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.DepartmentMapper;
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
@RequestMapping("/api/departments")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeePortalService employeePortalService;

    /**
     * get the list of all department of a company;
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllDeparmentOfCompany(){
        Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Department> departments = departmentService.getAllDepartment(CompanyUtil.getCompanyId());
            departments.forEach(item -> {
                Map<String, Object> departmentMap = null;
                try {
                    departmentMap = departmentMapper.getObjectMap(item);
                } catch (MapperException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                mapList.add(departmentMap);
            });
        }
        catch (NullPointerException e){
            LOGGER.error("Departments is null" ,e );
        }catch (DbException e){
            LOGGER.error(e.getMessage() , e);
        }
         responseMap.put("departments" , mapList);
         return ResponseEntity.ok().body(responseMap);
    }

    /**
     * get depart of a employee;
     * @param employeeId
     * @return
     */
    @RequestMapping(path = "/{employeeId}/department",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Map<String, Object>>>
    getEmployeeLocation(@PathVariable Long employeeId){

        Department department = null;
        try {
            department = employeePortalService.getEmployee(employeeId).getDepartment();
        } catch (DbException e) {
            LOGGER.error(e.getMessage() , e);
        }

        Map<String, Map<String, Object>> map = new HashMap<>();
        try {
            map.put("department", departmentMapper.getObjectMap(department) );
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() , e);
        }

        return ResponseEntity.ok().body(map);

    }

}
