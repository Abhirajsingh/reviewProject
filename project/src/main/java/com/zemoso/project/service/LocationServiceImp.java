package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Location;
import com.zemoso.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("LocationServiceImp")
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    /**
     * get all location of a company
     *
     * @param companyId
     * @return
     */
    public List<Location> getAllLocation(Long companyId) throws DbException {
        try {
            return locationRepository.findAllByCompanyId(companyId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/LocationService/getAllLocation", e);
        }
    }

    /**
     * find one location search by it's id
     *
     * @param locationId
     * @return
     */
    public Location getLocation(Long locationId) throws DbException {
        try {
            return locationRepository.findOne(locationId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/LocationService/getLocation", e);
        }
    }
}
