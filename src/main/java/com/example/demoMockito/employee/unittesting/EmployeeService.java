package com.example.demoMockito.employee.unittesting;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployeeByName(String name);

    public List<Employee> getAllEmployeees();

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(Employee employee);
}
