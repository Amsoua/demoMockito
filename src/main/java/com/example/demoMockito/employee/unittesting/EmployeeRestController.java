package com.example.demoMockito.employee.unittesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")

public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployeees();
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> addEmployee ( @RequestBody Employee employee)
    {
        //code
        employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }
}
