package com.zemoso.project.utils;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Project;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * convert project to map and vice-versa;
 */
@Component
public class ProjectMapper {

    /**
     * get Map from Object
     * @param project
     * @return
     */
    public Map<String, Object> getObjectMap(Project project) throws MapperException{
        Map<String , Object> map = new HashMap<>();
        try {
            if (project != null) {
                map.put(Constant.ID, project.getId());
                map.put(Constant.NAME, project.getName());
                map.put(Constant.COMPANY_ID, project.getCompanyId());
            }
            return map;
        }
        catch (Exception e){
            throw new MapperException("MapperException , /ProjectMapper/getObjectMap" ,e);
        }
    }

}
