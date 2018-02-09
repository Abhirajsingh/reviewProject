package com.zemoso.project.utils;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Role;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import java.util.Map;

@Component
public class RoleMapper {

    /**
     * get the map from Role Object;
     * @param role
     * @return
     */
    public Map<String,Object> getObjectMap(Role role) throws MapperException{
        try{
        Map<String , Object> map = new HashMap<>();
        map.put(Constant.ID , role.getId());
        map.put(Constant.NAME , role.getName());
        return map;}
        catch (Exception e){
            throw new MapperException("MapperException , /RoleMapper/getObjectMap" ,e);
        }
    }
}
