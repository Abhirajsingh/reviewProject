package com.zemoso.project.repository;

import com.zemoso.project.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location , Long>{
    List<Location> findAllByCompanyId(Long companyId);
}
