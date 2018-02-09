package com.zemoso.project.utils;

import com.zemoso.project.exception.MapperException;
import com.zemoso.project.model.Location;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * convert map to Location object and vice-versa;
 */
@Component
public class LocationMapper {

    /**
     * convert object into map
     * @param location
     * @return
     */
    public Map<String , Object> getObjectMap(Location location) throws MapperException{
        try {
            Map<String, Object> map = new HashMap<>();
            if (location != null) {
                map.put(Constant.ID, location.getId());
                map.put(Constant.COMPANY_ID, location.getCompanyId());
                map.put(Constant.COUNTRY, location.getCountry());
                map.put(Constant.STATE, location.getState());
                map.put(Constant.LANDMARK, location.getLandmark());
                map.put(Constant.CITY, location.getCity());
            }
            return map;
        }
        catch (Exception e){
            throw new MapperException("MapperException , /LocationMapper/getObjectMap" ,e);
        }
    }
}
