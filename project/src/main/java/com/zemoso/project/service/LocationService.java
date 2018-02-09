package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    /**
     * get all location of a company
     *
     * @param companyId
     * @return
     */
     List<Location> getAllLocation(Long companyId) throws DbException;

    /**
     * find one location search by it's id
     *
     * @param locationId
     * @return
     */
    Location getLocation(Long locationId) throws DbException;

}
