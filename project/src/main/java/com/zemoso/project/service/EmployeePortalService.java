package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeePortalService {
    /**
     * Save project to DB
     *
     * @param employee
     * @return
     */
    Employee save(Employee employee) throws DbException;
    /**
     * get list of All employee from db;
     *
     * @param companyId
     * @return
     */
    List<Employee> getAllEmployee(Long companyId) throws DbException;
    /**
     * get perticular employee from db, search by it's id ;
     *
     * @param employeeId
     * @return
     */
    Employee getEmployee(Long employeeId) throws DbException;
}
