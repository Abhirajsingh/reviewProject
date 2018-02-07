package com.zemoso.project.controller;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Role;
import com.zemoso.project.service.RoleService;
import com.zemoso.project.utils.CompanyUtil;
import com.zemoso.project.utils.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleMapper rolesMapper;

    @Autowired
    private RoleService roleService;

    /**
     * get all Role List;
     * @return <Map<String, Object>>
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity getAllRoles() {
        Map<String , Object> responseMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Role> roles = roleService.getAllRoles(CompanyUtil.getCompanyId());
            roles.forEach(role -> {
                try {
                    mapList.add(rolesMapper.getObjectMap(role));
                } catch (MapperException e) {
                    log.error(e.getMessage(), e);
                }
            });
            responseMap.put("roles", mapList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }catch (Exception e){
            log.error("roles is null" ,e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
