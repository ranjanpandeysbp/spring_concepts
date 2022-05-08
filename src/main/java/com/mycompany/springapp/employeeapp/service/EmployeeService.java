package com.mycompany.springapp.employeeapp.service;

import com.mycompany.springapp.employeeapp.model.EmployeeModel;
import com.mycompany.springapp.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository er;

    public List<EmployeeModel> getAllEmployees()
    {
        List<EmployeeModel> employeeModelList = er.getAllEmployees();
        return employeeModelList;
    }
    public EmployeeModel createEmployee(EmployeeModel employeeModel)
    {
        employeeModel = er.createEmployee(employeeModel);
        return employeeModel;
    }
    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel)
    {
        EmployeeModel employeeModel1 = er.updateEmployee(id, employeeModel);
        return employeeModel1;
    }
    public EmployeeModel deleteEmployee(Long id)
    {
        EmployeeModel employeeModel = er.deleteEmployee(id);
        return employeeModel;
    }
    public List<EmployeeModel> searchEmployeeBySalaryRange(Double minSalary, Double maxSalary)
    {
        List<EmployeeModel> searchEmployeeList = er.searchEmployeeBySalaryRange(minSalary, maxSalary);
        return searchEmployeeList;
    }
    public List<EmployeeModel> searchEmployeeByDepartment(String department)
    {
        List<EmployeeModel> searchEmployeeList = er.searchEmployeeByDepartment(department);
        return searchEmployeeList;
    }
}
