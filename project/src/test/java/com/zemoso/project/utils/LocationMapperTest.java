package com.zemoso.project.utils;

import com.zemoso.project.model.Location;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

import static org.junit.Assert.*;

/**
 * Unit test for LocationMapper Class in util;
 */
public class LocationMapperTest {

    /**
     * Test the getObjectMap method of LocationMapper;
     *
     * @throws Exception
     */
    @Test
    public void getObjectMap() throws Exception {
        Long id1 = new Long(1);
        Long id2 = new Long(2);
        Long companyId = CompanyUtil.getCompanyId();
        Location location1 = new Location(id1, companyId, "India", "Telangana", "Hyderabad", "Hyderabad");
        Map<String, Object> map1 = new HashMap<>();
        map1.put(Constant.ID, id1);
        map1.put(Constant.COMPANY_ID, companyId);
        map1.put(Constant.COUNTRY, "India");
        map1.put(Constant.STATE, "Telangana");
        map1.put(Constant.CITY, "Hyderabad");
        map1.put(Constant.LANDMARK, "Hyderabad");
        Map<String, Object> locationMap1 = new LocationMapper().getObjectMap(location1);
        assertEquals(map1, locationMap1);

        Location location2 = new Location(id2, companyId, "India", "Karnataka", "Bangalore", "Bangalore");
        Map<String, Object> map2 = new HashMap<>();
        map2.put(Constant.ID, id2);
        map2.put(Constant.COMPANY_ID, companyId);
        map2.put(Constant.COUNTRY, "India");
        map2.put(Constant.STATE, "Karnataka");
        map2.put(Constant.CITY, "Bangalore");
        map2.put(Constant.LANDMARK, "Bangalore");
        Map<String, Object> locationMap2 = new LocationMapper().getObjectMap(location2);
        assertEquals(map2, locationMap2);
    }

}