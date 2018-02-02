package com.zemoso.project.controller;


import com.zemoso.project.exception.DbException;
import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Role;
import com.zemoso.project.service.RoleService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleMapper rolesMapper;

    @Autowired
    private RoleService roleService;

    /**
     * get all Role List;
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllRoles() {
        Map<String , Object> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Role> roles = roleService.getAllRoles(CompanyUtil.getCompanyId());
            roles.forEach(role -> {
                try {
                    mapList.add(rolesMapper.getObjectMap(role));
                } catch (MapperException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        }catch (DbException e){
            LOGGER.error(e.getMessage(), e);
        }catch (Exception e){
            LOGGER.error("roles is null" ,e);
        }
        responseMap.put("roles", mapList);
        return ResponseEntity.ok().body(responseMap);
    }
}
