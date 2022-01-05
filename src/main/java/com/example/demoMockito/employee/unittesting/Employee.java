package com.example.demoMockito.employee.unittesting;

import javax.persistence.*;


@Entity
@Table(name = "person")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    public Employee(Long id,  String name) {
        this.id = id;
        this.name = name;
    }

    public Employee( String name) {
        this.name = name;
    }

    // standard getters and setters, constructors


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}