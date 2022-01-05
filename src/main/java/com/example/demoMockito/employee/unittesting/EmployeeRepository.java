package com.example.demoMockito.employee.unittesting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

     Employee findByName(String name);

     List<Employee> findAll();
}