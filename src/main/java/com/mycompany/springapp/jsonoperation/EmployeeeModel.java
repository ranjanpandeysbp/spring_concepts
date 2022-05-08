package com.mycompany.springapp.jsonoperation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeeModel {

    @JsonProperty("empId")
    public Integer empId1;
    public String name;
    //@JsonIgnore
    public Long phone;
    //@JsonIgnore
    public String password;

    public Integer getEmpId1() {
        return empId1;
    }

    public void setEmpId1(Integer empId1) {
        this.empId1 = empId1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
