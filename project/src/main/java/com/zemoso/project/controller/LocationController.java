package com.zemoso.project.controller;


import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Location;

import com.zemoso.project.service.EmployeePortalService;
import com.zemoso.project.service.LocationService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.LocationMapper;
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
@RequestMapping("/api/locations")
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);


    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private EmployeePortalService employeePortalService;

    /**
     * get the list of all location of a company;
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllLocationOfCompany(){
        Map<String,List<Map<String,Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Location> locations = locationService.getAllLocation(CompanyUtil.getCompanyId());
        locations.forEach(item ->{
            Map<String , Object> departmentMap = null;
            try {
                departmentMap = locationMapper.getObjectMap(item);
            } catch (MapperException e) {
                LOGGER.error(e.getMessage() ,e);
            }
            mapList.add(departmentMap);
        });}
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        responseMap.put("locations" , mapList);
        return ResponseEntity.ok().body(responseMap);
    }
    /**
     * get depart of a employee;
     * @param employeeId
     * @return
     */
    @RequestMapping(path = "/{employeeId}/location",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Map<String, Object>>>
    getEmployeeLocation(@PathVariable Long employeeId){

        Location location = null;
        try {
            location = employeePortalService.getEmployee(employeeId).getLocation();
        } catch (DbException e) {
            LOGGER.error(e.getMessage() ,e);
        }

        Map<String, Map<String, Object>> map = new HashMap<>();
        try {
            map.put("location", locationMapper.getObjectMap(location) );
        } catch (MapperException e) {
            LOGGER.error(e.getMessage() ,e);
        }

        return ResponseEntity.ok().body(map);

    }
}
