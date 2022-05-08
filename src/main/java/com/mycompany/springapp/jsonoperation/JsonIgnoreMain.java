package com.mycompany.springapp.jsonoperation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonIgnoreMain {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            EmployeeeModel employeeeModel =new EmployeeeModel();
            employeeeModel.setEmpId1(1111);
            employeeeModel.setName("John Doe");
            //employeeeModel.setPassword("mysecret");
            //employeeeModel.setPhone(9876543210L);

            //convert java object to json // serialization
            String jsonString = mapper.
                    writerWithDefaultPrettyPrinter().
                    writeValueAsString(employeeeModel);

            System.out.println("JSON version of employee object");
            System.out.println(jsonString);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
