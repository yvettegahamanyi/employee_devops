package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;


    @Column(name = "location")
    private String location;

    @JsonIgnore
    @Column(name = "password")
    private String password;
}
