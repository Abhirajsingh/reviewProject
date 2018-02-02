package com.zemoso.project.utils;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Department;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * mapper for Department model
 */
@Component
public class DepartmentMapper {
    /**
     * convert Department object into Map < String,Object >;
     * @param department
     * @return
     */
    public Map<String , Object> getObjectMap(Department department) throws MapperException{
        Map<String , Object> map = new HashMap<>();
        try {
            if (department != null) {
                map.put(Constant.ID, department.getId());
                map.put(Constant.COMPANY_ID, department.getCompanyId());
                map.put(Constant.NAME, department.getName());
            }
            return map;
        }
        catch (Exception e){
            throw new MapperException("MapperException /DepartmentMapper/getObjectMap" , e);
        }
    }

}
