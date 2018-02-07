package com.zemoso.project.controller;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Department;
import com.zemoso.project.service.DepartmentService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.DepartmentMapper;
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
@RequestMapping("/api/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;


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
                    log.error(e.getMessage(), e);
                }
                mapList.add(departmentMap);
            });
        }catch (Exception e){
            log.error(e.getMessage() , e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }
         responseMap.put("departments" , mapList);
         return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    /**
     * get department  of a employee;
     * @param departmentId
     * @return <Map<String, Map<String, Object>>>
     */
    @RequestMapping(path = "/department/{departmentId}",method = RequestMethod.GET)
    public ResponseEntity getEmployeeLocation(@PathVariable Long departmentId){
        try {
        Department department = departmentService.getDepartment(departmentId);
        Map<String, Map<String, Object>> map = new HashMap<>();

            map.put("department", departmentMapper.getObjectMap(department) );
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e) {
            log.error(e.getMessage() , e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(e.getMessage());
        }

    }

}
