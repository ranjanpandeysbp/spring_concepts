package com.mycompany.springapp.employeeapp.model;

import javax.persistence.*;

@Entity(name = "EMPLOYEE_TABLE")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private Long id;
    @Column(name = "EMPLOYEE_NAME")
    private String name;
    @Column(name = "EMPLOYEE_SALARY")
    private Double salary;
    @Column(name = "EMPLOYEE_DEPARTMENT")
    private String department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
