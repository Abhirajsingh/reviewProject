package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Department;
import com.zemoso.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("DepartmentServiceImp")
public class DepartmentServiceImp implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * get the list of all department of a company;
     *
     * @param companyId
     * @return
     */
    public List<Department> getAllDepartment(Long companyId) throws DbException {
        try {
            return departmentRepository.findAllByCompanyId(companyId);
        } catch (Exception e) {
            throw new DbException("Exception at /service/DepartmentService/getAllDepartment", e);
        }
    }

    /**
     * get the department search by it's id;
     *
     * @param departmentId
     * @return
     */
    public Department getDepartment(Long departmentId) throws DbException {
        try {
            return departmentRepository.findOne(departmentId);
        } catch (Exception e) {
            throw new DbException("Exception at /service/DepartmentService/getDepartment", e);
        }
    }
}
