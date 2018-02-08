package com.zemoso.project.service;

import com.zemoso.project.model.Location;
import com.zemoso.project.utils.CompanyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImpTest {

    @Mock
    private LocationServiceImp locationServiceImp;
    @Test
    public void getLocation() throws Exception {
        Long id1 = new Long(1);
        Long companyId = CompanyUtil.getCompanyId();
        Long id2 = new Long(2);

        Location location1 = new Location(id1, companyId , "India" , "Telangana" ,"Hyderabad" , "Hyderabad");
        Mockito.when(locationServiceImp.getLocation(id1)).thenReturn(new Location(id1, companyId , "India" , "Telangana" ,"Hyderabad" , "Hyderabad"));
        Location location2 = new Location(id2, companyId , "India" , "Maharastra" ,"Pune" , "Pune");
        Mockito.when(locationServiceImp.getLocation(id2)).thenReturn(new Location(id2, companyId , "India" , "Maharastra" ,"Pune" , "Pune"));
        assertEquals(location1 , locationServiceImp.getLocation(id1));
        assertEquals(location2 , locationServiceImp.getLocation(id2));
    }

}