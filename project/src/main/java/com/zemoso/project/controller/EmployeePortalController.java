package com.zemoso.project.controller;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Employee;
import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.Constant;
import com.zemoso.project.utils.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeePortalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePortalController.class);


    @Autowired
    private EmployeePortalService employeePortalService;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * rest controller to get all employee data from db;
     * @return
     */
   @RequestMapping(method= RequestMethod.GET)
   public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllEmployeeOfCompany(){
       Long companyId = CompanyUtil.getCompanyId();
       Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
       List<Map<String, Object>> mapList = new ArrayList<>();
       try{
               List<Employee> employees = employeePortalService.getAllEmployee(companyId);
           employees.forEach(item->{
           Map<String, Object> employeeMap = null;
               try {
                   employeeMap = employeeMapper.getObjectMap(item);
               } catch (MapperException e) {
                   LOGGER.error(e.getMessage() ,e);
               }
               mapList.add(employeeMap);
       });}
       catch (NullPointerException e){
           LOGGER.error(e.getMessage());
       }
       catch (DbException e){
           LOGGER.error(e.getMessage() , e);
       }


       responseMap.put("employees" , mapList);
       return ResponseEntity.ok().body(responseMap);

    }
    /**
     * Rest controller to get selected employee
     *
     * @param employeeId
     * @return
     */
    @RequestMapping(path = "/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getProjectsById(@PathVariable Long employeeId) {
        Employee employee = null;
        try {
            employee = employeePortalService.getEmployee(employeeId);
        } catch (DbException e) {
          LOGGER.error(e.getMessage() ,e);
        }

        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put(Constant.EMPLOYEE, employeeMapper.getObjectMap(employee));
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() , e);
        }

        return ResponseEntity.ok().body(responseMap);
    }
    /**
     * REST controller to add employee in db
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Map<String , Map> map){
        Map<String, Object> employeeMap = map.get(Constant.EMPLOYEE);
        Employee employee = null;
        try {
            employee = employeeMapper.getMapObject(employeeMap);
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() ,e);
        }
        try {
            employeePortalService.save(employee);
        } catch (DbException e) {
            LOGGER.error(e.getMessage() ,e);
        }
        Map<String, Object> emap = null;
        try {
            emap = employeeMapper.getObjectMap(employee);
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() , e);
        }
        Map<String ,Object> responseMap = new HashMap<>();
        responseMap.put(Constant.EMPLOYEE ,emap );

        return ResponseEntity.ok().body(responseMap);
    }

    //TODO
    //write controller for the update the data

    /**
     * REST controller to update/edit a particular employee data
     *
     */
   @RequestMapping(path = "/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String,Object>> updateEmployee
   (@RequestBody Map<String ,Object> map , @PathVariable Long employeeId){

       try {
           Employee ipEmployee = employeeMapper.getMapObject(map);
           Employee dbEmployee = employeePortalService.getEmployee(employeeId);
           dbEmployee.setFirstName(ipEmployee.getFirstName());
           dbEmployee.setMiddleName(ipEmployee.getMiddleName());
           dbEmployee.setLastName(ipEmployee.getLastName());
           dbEmployee.setBiodata(ipEmployee.getBiodata());
           dbEmployee.setSkill(ipEmployee.getSkill());
           dbEmployee.setEmail(ipEmployee.getEmail());
           dbEmployee.setMobileNo(ipEmployee.getMobileNo());
           dbEmployee.setDepartment(ipEmployee.getDepartment());
           dbEmployee.setProject(ipEmployee.getProject());
           dbEmployee.setEmployeeRole(ipEmployee.getEmployeeRole());
           dbEmployee.setLocation(ipEmployee.getLocation());
           dbEmployee.setStartDate(ipEmployee.getStartDate());
           dbEmployee.setReportingEmployeeName(ipEmployee.getReportingEmployeeName());
           employeePortalService.save(dbEmployee);

           Map<String, Object> responseMap = new HashMap<>();
               responseMap.put(Constant.EMPLOYEE, employeeMapper.getObjectMap(dbEmployee));

           }
       catch (Exception e){
           LOGGER.error(e.getMessage(),e);
       }
       return ResponseEntity.ok().body(map);
   }

}
