package com.mycompany.springapp.jsonoperation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonToJavaToJsonMain {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            EmployeeeModel employeeeModel =new EmployeeeModel();
            employeeeModel.setEmpId1(1111);
            employeeeModel.setName("John Doe");
            employeeeModel.setPassword("mysecret");
            employeeeModel.setPhone(9876543210L);

            //convert java object to json
            //String jsonString = mapper.writeValueAsString(employeeeModel);
            String jsonString = "{\"deptId\":4445,\"empId\":1111,\"name\":\"John Doe\",\"password\":\"mysecret\"}";

            System.out.println("JSON version of employee object");
            System.out.println(jsonString);

            //convert json string to java object //deserialization
            EmployeeeModel emp = mapper.readValue(jsonString, EmployeeeModel.class);
            System.out.println("Object version of employee json: "+emp.getName());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
