package com.zemoso.project.utils;
import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.*;
import com.zemoso.project.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class EmployeeMapper {


    @Autowired
    private EmployeePortalService employeePortalService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private RoleService roleService;


    public Map<String , Object> getObjectMap(Employee employee) throws MapperException{
        Map<String , Object> map = new HashMap<>();
        try{
            Map<String,String> roleMap = new HashMap<>();
            roleMap.put(Constant.NAME,employee.getEmployeeRole());
            Map<String, String> reportingName = new HashMap<>();
            reportingName.put(Constant.NAME , employee.getReportingEmployeeName());
            map.put(Constant.ID , employee.getId());
            map.put(Constant.NAME , employee.getFullName());
            map.put(Constant.BIODATA , employee.getBiodata());
            map.put(Constant.EMAIL , employee.getEmail());
            map.put(Constant.MOBILE_NO, employee.getMobileNo());
            map.put(Constant.EMPLOYEE_ROLE, roleMap);
            map.put(Constant.START_DATE, employee.getStartDate());
            map.put(Constant.PROFILE_PIC , employee.getProfilePic());
            map.put(Constant.REPORTING_EMPLOYEE_ID , employee.getReportingEmployeeId());
            map.put(Constant.REPORTING_EMP_NAME ,reportingName);
            map.put(Constant.FIRST_NAME , employee.getFirstName());
            map.put(Constant.MIDDLE_NAME , employee.getMiddleName());
            map.put(Constant.LAST_NAME , employee.getLastName());
            Map<String, String> links = new HashMap<>();
            links.put("skills", "/api/skills/" + employee.getId() + "/skills");
            links.put("location", "/api/locations/location/" + employee.getLocationId());
            links.put("department", "/api/departments/department/" + employee.getDepartmentId());
            links.put("project", "/api/projects/project/" + employee.getProjectId());
            map.put("links", links);
            return map;
        }
        catch (Exception e){
            throw new MapperException("MapperException , /EmployeeMapper/getObjectMap" ,e);
        }
    }
    public Employee getMapObject(Map<String, Object> map) throws MapperException{
        Employee employee = new Employee();

        String[] keys = {Constant.FIRST_NAME, Constant.MIDDLE_NAME, Constant.LAST_NAME,
                        Constant.BIODATA , Constant.EMAIL , Constant.MOBILE_NO , Constant.START_DATE ,
                        Constant.REPORTING_EMPLOYEE_ID , Constant.PROFILE_PIC
                            };
        try {
            for(String key : keys){
                if(map.containsKey(key) && map.get(key) !=null){
                    BeanUtils.setProperty(employee , key , map.get(key).toString());
                }
            }

            if (map.containsKey(Constant.SKILLS) && map.get(Constant.SKILLS) != null) {
                List<Map<String, Object>> skillMapList = (List<Map<String, Object>>) map.get(Constant.SKILLS);
                Set<Skill> skillSet = new HashSet<>();

                    skillMapList.forEach(skillMap -> {
                        Long skillId = Long.parseLong(skillMap.get(Constant.ID).toString());
                        try {
                            skillSet.add(skillService.getSkill(skillId));
                        } catch (DbException e) {
                            log.error(e.getMessage() ,e);
                        }
                    });
                    employee.setSkill(skillSet);
            }


            if (map.containsKey(Constant.PROJECT) && map.get(Constant.PROJECT) != null) {
                Map<String, Object> projectMap = (Map<String, Object>) map.get(Constant.PROJECT);
                Long projectId = Long.parseLong(projectMap.get(Constant.ID).toString());
                employee.setProject(projectService.getProject(projectId));
            }

            if (map.containsKey(Constant.DEPARTMENT) && map.get(Constant.DEPARTMENT) != null) {
                Map<String, Object> departmentMap = (Map<String, Object>) map.get(Constant.DEPARTMENT);
                Long departmentId = Long.parseLong(departmentMap.get(Constant.ID).toString());
                employee.setDepartment(departmentService.getDepartment(departmentId));
            }


            if (map.containsKey(Constant.EMPLOYEE_ROLE) && map.get(Constant.EMPLOYEE_ROLE) != null) {
                    Object responseMap = map.get(Constant.EMPLOYEE_ROLE);
                    if (responseMap instanceof Map) {
                        Map<String, Object> roleMap = (Map<String, Object>) responseMap;
                        employee.setEmployeeRole(roleMap.get(Constant.NAME).toString());
                    } else {
                        employee.setEmployeeRole(map.get(Constant.EMPLOYEE_ROLE).toString());
                    }
                }


            if (map.containsKey(Constant.LOCATION) && map.get(Constant.LOCATION) != null) {
                Map<String, Object> locationMap = (Map<String, Object>) map.get(Constant.LOCATION);
                Long locationId = Long.parseLong(locationMap.get(Constant.ID).toString());
                employee.setLocation(locationService.getLocation(locationId));
            }

            if (map.containsKey(Constant.REPORTING_EMP_NAME) && map.get(Constant.REPORTING_EMP_NAME) != null) {

                Object responseMap = map.get(Constant.REPORTING_EMP_NAME);
                if(responseMap instanceof Map){
                    Map<String , Object> reportingMap = (Map<String, Object>) responseMap;
                    if(reportingMap.containsKey(Constant.REPORTING_EMP_NAME) && reportingMap.get(Constant.REPORTING_EMP_NAME) !=null)
                    employee.setReportingEmployeeName(reportingMap.get(Constant.NAME).toString());
                }
                else {
                    if(map.containsKey(Constant.REPORTING_EMP_NAME) && map.get(Constant.REPORTING_EMP_NAME) !=null)
                    employee.setReportingEmployeeName(map.get(Constant.REPORTING_EMP_NAME).toString());
                }
            }

            employee.setCreatedById(CompanyUtil.getCompanyId());
            employee.setLastUpdatedById(CompanyUtil.getCompanyId());
            employee.setCompanyId(CompanyUtil.getCompanyId());

            return employee;
        }
        catch (Exception e){
            throw new MapperException("Exception , EmployeeMapper/getMapObject" , e);
        }
    }


}
