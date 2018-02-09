package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    /**
     * get the list of role available in a company;
     * @param companyId
     * @return
     */
     List<Role> getAllRoles(Long companyId) throws DbException;
    /**
     * get one Role search by roleId
     * @param roleId
     * @return
     */
    Role getRole(Long roleId) throws DbException;
}
