package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    /**
     * get the list of all department of a company;
     *
     * @param companyId
     * @return
     */
    List<Department> getAllDepartment(Long companyId) throws DbException;
    /**
     * get the department search by it's id;
     *
     * @param departmentId
     * @return
     */
    Department getDepartment(Long departmentId) throws DbException;
}
