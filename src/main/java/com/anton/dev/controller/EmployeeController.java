package com.anton.dev.controller;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.anton.dev.model.Employee;
 
@RestController
public class EmployeeController 
{
    @RequestMapping("/")
    public List<Employee> getEmployees() 
    {
    	System.out.println("getEmployees...........");
        List<Employee> employeesList = new ArrayList<Employee>();
        employeesList.add(new Employee(1, "lokesh", "gupta", "howtodoinjava@gmail.com"));
        return employeesList;
    }
}

