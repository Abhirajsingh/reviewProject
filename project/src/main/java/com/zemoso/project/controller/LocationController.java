package com.zemoso.project.controller;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Location;

import com.zemoso.project.service.LocationService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.LocationMapper;
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
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationMapper locationMapper;


    /**
     * get the list of all location of a company;
     *
     * @return <Map<String, List<Map<String, Object>>>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllLocationOfCompany() {
        Map<String, List<Map<String, Object>>> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Location> locations = locationService.getAllLocation(CompanyUtil.getCompanyId());
            locations.forEach(item -> {
                Map<String, Object> departmentMap = null;
                try {
                    departmentMap = locationMapper.getObjectMap(item);
                } catch (MapperException e) {
                    log.error(e.getMessage(), e);
                }
                mapList.add(departmentMap);
            });
            responseMap.put("locations", mapList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * get location of a employee;
     *
     * @param locationId
     * @return <Map<String, Map<String, Object>>>
     */
    @RequestMapping(path = "/location/{locationId}", method = RequestMethod.GET)
    public ResponseEntity getEmployeeLocation(@PathVariable Long locationId) {

        Location location = null;
        try {
            location = locationService.getLocation(locationId);
            Map<String, Map<String, Object>> map = new HashMap<>();
            map.put("location", locationMapper.getObjectMap(location));
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
