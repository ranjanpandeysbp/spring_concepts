package com.mycompany.springapp.employeeapp.repository;

import com.mycompany.springapp.employeeapp.model.EmployeeModel;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();

    public List<EmployeeModel> getAllEmployees()
    {
        /*
        EmployeeModel em = new EmployeeModel();
        em.setId(123L);
        em.setName("Justin Tripathy");
        em.setSalary(20000.50);
        em.setDepartment("Sales");
        employeeList.add(em);

        em = new EmployeeModel();

        em.setId(124L);
        em.setName("Rohit Thomas");
        em.setSalary(30000.50);
        em.setDepartment("Sales");
        employeeList.add(em);

        em = new EmployeeModel();

        em.setId(125L);
        em.setName("Kevin Mishra");
        em.setSalary(25000.50);
        em.setDepartment("Marketing");
        employeeList.add(em);
        */

        return employeeList;
    }

    public EmployeeModel createEmployee(EmployeeModel employeeModel)
    {
        this.employeeList.add(employeeModel);
        return employeeModel;
    }
    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel)
    {
        EmployeeModel employeeModel1 = null;
        for(int i = 0; i<this.employeeList.size(); i++)
        {
            if(this.employeeList.get(i).getId() == id)
            {
                employeeModel1 = this.employeeList.get(i);
                employeeModel1.setName(employeeModel.getName());
                employeeModel1.setSalary(employeeModel.getSalary());
                employeeModel1.setDepartment(employeeModel.getDepartment());
                this.employeeList.set(i, employeeModel1);
                break;
            }
        }
        return employeeModel1;
    }
    public EmployeeModel deleteEmployee(Long id)
    {
        EmployeeModel employeeModel = null;
        for(int i = 0; i<this.employeeList.size(); i++)
        {
            if(this.employeeList.get(i).getId() == id)
            {
                employeeModel = this.employeeList.get(i);
                this.employeeList.remove(i);
                break;
            }
        }
        return employeeModel;
    }
    public List<EmployeeModel> searchEmployeeBySalaryRange(Double minSalary, Double maxSalary)
    {
        List<EmployeeModel> searchEmployeeList = new ArrayList<EmployeeModel>();
        EmployeeModel employeeModel = null;
        for(int i = 0; i<this.employeeList.size(); i++)
        {
            employeeModel = this.employeeList.get(i);
            if(employeeModel.getSalary() >= minSalary && employeeModel.getSalary() <= maxSalary)
            {
                searchEmployeeList.add(employeeModel);
            }
        }
        return searchEmployeeList;
    }
    public List<EmployeeModel> searchEmployeeByDepartment(String department)
    {
        List<EmployeeModel> searchEmployeeList = new ArrayList<EmployeeModel>();
        EmployeeModel employeeModel = null;
        for(int i = 0; i<this.employeeList.size(); i++)
        {
            employeeModel = this.employeeList.get(i);
            if(employeeModel.getDepartment().contains(department))
            {
                searchEmployeeList.add(employeeModel);
            }
        }
        return searchEmployeeList;
    }
}
