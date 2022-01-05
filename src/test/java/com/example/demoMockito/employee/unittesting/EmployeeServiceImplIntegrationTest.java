package com.example.demoMockito.employee.unittesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplIntegrationTest {

    @InjectMocks
    private EmployeeServiceImpl testedClass;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void setUp() {

        Employee alex = new Employee("alex");
        Mockito.when(employeeRepository.findByName(alex.getName()))
                .thenReturn(alex);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = testedClass.getEmployeeByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void whenFindAll_thenOk()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee( "John");
        Employee empTwo = new Employee( "kolenchiski");
        Employee empThree = new Employee( "Waugh");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.findAll()).thenReturn(list);

        //test
        List<Employee> empList = testedClass.getAllEmployeees();

        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).findAll();
    }



    @Test
    public void givenAnEmployee_whenSave_thenOk()
    {
        Employee employee = new Employee("Gupta");

        testedClass.saveEmployee(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void givenAnEmployee_whenUpdate_thenOk()
    {
        Employee employee = new Employee("Gupta");

        testedClass.updateEmployee(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void givenExistingEmployee_whenDeleteEmployee_thenOk()
    {

        Employee employee = new Employee("Gupta");
        testedClass.deleteEmployee(employee);
        verify(employeeRepository, times(1)).delete(employee);

    }

}