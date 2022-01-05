package com.example.demoMockito.employee.unittesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerIntegrationTest {

    @InjectMocks EmployeeRestController controller;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

   @Before
   public void setUp(){
       MockitoAnnotations.openMocks(this);
       mvc = MockMvcBuilders.standaloneSetup(this.controller).build();
   }
    // write test cases here

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        Employee alex = new Employee("alex");

        List<Employee> allEmployees = Arrays.asList(alex);

        when(service.getAllEmployeees()).thenReturn(allEmployees);


        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(alex.getName()));
    }

    @Test
    public void createEmployeeAPI() throws Exception {

       Employee alex = new Employee(1L,"alex");

       //when(service.saveEmployee(alex)).thenReturn(alex);

       ObjectMapper objectMapper = new ObjectMapper();
       
        mvc.perform( MockMvcRequestBuilders
                .post("/api/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alex))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
