package com.zemoso.project.utils;

import com.zemoso.project.model.Location;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

import static org.junit.Assert.*;

public class LocationMapperTest {
    @Test
    public void getObjectMap() throws Exception {
        Long id = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Location location = new Location(id, companyId , "India" , "Telangana" ,"Hyderabad" , "Hyderabad");
        Map<String , Object> map = new HashMap<>();
        map.put(Constant.ID, id);
        map.put(Constant.COMPANY_ID , companyId);
        map.put(Constant.COUNTRY , "India");
        map.put(Constant.STATE , "Telangana" );
        map.put(Constant.CITY , "Hyderabad");
        map.put(Constant.LANDMARK , "Hyderabad");
        Map<String , Object> locationMap = new LocationMapper().getObjectMap(location);
        assertEquals(map, locationMap);
    }

}