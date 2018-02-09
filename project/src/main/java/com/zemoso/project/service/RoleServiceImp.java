package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Role;
import com.zemoso.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("RoleServiceImp")
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * get the list of role available in a company;
     *
     * @param companyId
     * @return
     */
    public List<Role> getAllRoles(Long companyId) throws DbException {
        try {
            return roleRepository.findAllByCompanyId(companyId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/RoleService/getAllRoles", e);
        }
    }

    /**
     * get one Role search by roleId
     *
     * @param roleId
     * @return
     */
    public Role getRole(Long roleId) throws DbException {
        try {
            return roleRepository.findOne(roleId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/RoleService/getRole", e);
        }
    }

}
