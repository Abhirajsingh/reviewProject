package com.zemoso.project.utils;
import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.*;
import com.zemoso.project.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMapper.class);


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
            links.put("location", "/api/locations/" + employee.getId() +"/location");
            links.put("department", "/api/departments/" + employee.getId() +"/department");
            links.put("project", "/api/projects/" + employee.getId() +"/project");
            map.put("links", links);
            return map;
        }
        catch (Exception e){
            throw new MapperException("MapperException , /EmployeeMapper/getObjectMap" ,e);
        }
    }
    public Employee getMapObject(Map<String, Object> map) throws MapperException{
        Employee employee = new Employee();

        try {

            if (map.containsKey(Constant.FIRST_NAME) && map.get(Constant.FIRST_NAME) != null)
                employee.setFirstName(map.get(Constant.FIRST_NAME).toString());

            if (map.containsKey(Constant.MIDDLE_NAME) && map.get(Constant.MIDDLE_NAME) != null)
                employee.setMiddleName(map.get(Constant.MIDDLE_NAME).toString());

            if (map.containsKey(Constant.LAST_NAME) && map.get(Constant.LAST_NAME) != null)
                employee.setLastName(map.get(Constant.LAST_NAME).toString());

            if (map.containsKey(Constant.BIODATA) && map.get(Constant.BIODATA) != null)
                employee.setBiodata(map.get(Constant.BIODATA).toString());

            if (map.containsKey(Constant.SKILLS) && map.get(Constant.SKILLS) != null) {
                List<Map<String, Object>> skillMapList = (List<Map<String, Object>>) map.get(Constant.SKILLS);
                Set<Skill> skillSet = new HashSet<>();

                    skillMapList.forEach(skillMap -> {
                        Long skillId = Long.parseLong(skillMap.get(Constant.ID).toString());
                        try {
                            skillSet.add(skillService.getSkill(skillId));
                        } catch (DbException e) {
                            LOGGER.error(e.getMessage() ,e);
                        }
                    });
                    employee.setSkill(skillSet);
            }

            if (map.containsKey(Constant.EMAIL) && map.get(Constant.EMAIL) != null)
                employee.setEmail(map.get(Constant.EMAIL).toString());

            if (map.containsKey(Constant.MOBILE_NO) && map.get(Constant.MOBILE_NO) != null)
                employee.setMobileNo(map.get(Constant.MOBILE_NO).toString());

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
                Map<String ,Object> roleMap = (Map<String, Object>) map.get(Constant.EMPLOYEE_ROLE);
                employee.setEmployeeRole(roleMap.get(Constant.NAME).toString());
            }

            if (map.containsKey(Constant.LOCATION) && map.get(Constant.LOCATION) != null) {
                Map<String, Object> locationMap = (Map<String, Object>) map.get(Constant.LOCATION);
                Long locationId = Long.parseLong(locationMap.get(Constant.ID).toString());
                employee.setLocation(locationService.getLocation(locationId));
            }

            if (map.containsKey(Constant.START_DATE) && map.get(Constant.START_DATE) != null)
                employee.setStartDate(map.get(Constant.START_DATE).toString());

            if (map.containsKey(Constant.REPORTING_EMPLOYEE_ID) && map.get(Constant.REPORTING_EMPLOYEE_ID) != null) {
                employee.setReportingEmployeeId(Long.parseLong(map.get(Constant.REPORTING_EMPLOYEE_ID).toString()));
            }
            if (map.containsKey(Constant.REPORTING_EMP_NAME) && map.get(Constant.REPORTING_EMP_NAME) != null) {
                Map<String ,Object> reportingNamMap = (Map<String, Object>) map.get(Constant.REPORTING_EMP_NAME);
                employee.setReportingEmployeeName(reportingNamMap.get(Constant.NAME).toString());
            }

            if (map.containsKey(Constant.PROFILE_PIC))
                employee.setProfilePic(map.get(Constant.PROFILE_PIC).toString());

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
