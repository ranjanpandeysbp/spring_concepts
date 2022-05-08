package com.mycompany.springapp.employeeapp.controller;

import com.mycompany.springapp.employeeapp.model.EmployeeModel;
import com.mycompany.springapp.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService es;

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees()
    {
        List<EmployeeModel> employeeModelList = es.getAllEmployees();
        ResponseEntity<List<EmployeeModel>> res = new ResponseEntity<List<EmployeeModel>>(employeeModelList, HttpStatus.OK);

        return res;
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel)
    {
        employeeModel = es.createEmployee(employeeModel);
        ResponseEntity<EmployeeModel> res = new ResponseEntity<EmployeeModel>(employeeModel,HttpStatus.CREATED);

        return res;
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable("id") Long id,
                                                        @RequestBody EmployeeModel employeeModel)
    {
        EmployeeModel employeeModel1 = es.updateEmployee(id, employeeModel);
        ResponseEntity<EmployeeModel> res = new ResponseEntity<EmployeeModel>(employeeModel1, HttpStatus.OK);

        return res;
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<EmployeeModel> deleteEmployee(@PathVariable Long id)
    {
        EmployeeModel employeeModel = es.deleteEmployee(id);
        ResponseEntity<EmployeeModel> res =new ResponseEntity<EmployeeModel>(employeeModel, HttpStatus.NO_CONTENT);

        return res;
    }

    @GetMapping(path = "/employees/searchBySal")
    public ResponseEntity<List<EmployeeModel>> searchEmployeeBySalaryRange(@RequestParam("minSal") Double minSalary,
                                                                     @RequestParam("maxSal") Double maxSalary)
    {
        List<EmployeeModel> searchEmployeeList = es.searchEmployeeBySalaryRange(minSalary, maxSalary);
        ResponseEntity<List<EmployeeModel>> res =new ResponseEntity<List<EmployeeModel>>(searchEmployeeList, HttpStatus.OK);

        return res;
    }

    @GetMapping(path = "/employees/searchByDept")
    public ResponseEntity<List<EmployeeModel>> searchEmployeeByDepartment(@RequestParam("dept") String department)
    {
        List<EmployeeModel> searchEmployeeList = es.searchEmployeeByDepartment(department);
        ResponseEntity<List<EmployeeModel>> res =new ResponseEntity<List<EmployeeModel>>(searchEmployeeList, HttpStatus.OK);

        return res;
    }
}
