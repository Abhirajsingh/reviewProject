package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Employee;
import com.zemoso.project.repository.EmployeeRepository;
import com.zemoso.project.utils.CompanyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("EmployeePortalServiceImp")
public class EmployeePortalServiceImp implements EmployeePortalService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Save project to DB
     *
     * @param employee
     * @return
     */
    public Employee save(Employee employee) throws DbException {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new DbException("Exception , /service/EmployeePortalService/save", e);
        }
    }

    /**
     * get list of All employee from db;
     *
     * @param companyId
     * @return
     */
    public List<Employee> getAllEmployee(Long companyId) throws DbException {
        try {
            return employeeRepository.findAllByCompanyIdOrderByLastUpdatedDesc(companyId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/EmployeePortalService/getAllEmployee", e);
        }
    }

    /**
     * get perticular employee from db, search by it's id ;
     *
     * @param employeeId
     * @return
     */
    public Employee getEmployee(Long employeeId) throws DbException {
        try {
            return employeeRepository.findOne(employeeId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/EmployeePortalService/getEmployee", e);
        }
    }


    /**
     * validate the employee by it's id from db ;
     *
     * @param employeeId
     * @return ; true if employee exit else false
     */
    public boolean validateEmployeeById(Long employeeId) throws DbException{
        try {
            List<Employee> employeeList = getAllEmployee(CompanyUtil.getCompanyId());
            for (Employee employee : employeeList) {
                if (employee.getId().equals(employeeId)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (Exception e) {
            throw new DbException("Exception , " +
                    "/service/EmployeePortalService/validateEmployeeById", e);
        }
        return false;
    }

}
