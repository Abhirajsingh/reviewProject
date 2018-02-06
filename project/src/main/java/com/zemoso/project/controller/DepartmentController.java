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
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity getAllDeparmentOfCompany(){
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
        }catch (Exception e){
            LOGGER.error(e.getMessage() , e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body(e.getMessage());
        }
         responseMap.put("departments" , mapList);
         return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    /**
     * get department  of a employee;
     * @param employeeId
     * @return <Map<String, Map<String, Object>>>
     */
    @RequestMapping(path = "/{employeeId}/department",method = RequestMethod.GET)
    public ResponseEntity getEmployeeLocation(@PathVariable Long employeeId){
        try {
        Department department = employeePortalService.getEmployee(employeeId).getDepartment();
        Map<String, Map<String, Object>> map = new HashMap<>();

            map.put("department", departmentMapper.getObjectMap(department) );
            return ResponseEntity.ok().body(map);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage() , e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body(e.getMessage());
        }

    }

}
