package com.example.employee.utils.dtos;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NotFound;

@Data
public class RegisterEmployee {

    public RegisterEmployee(String fullNames, String email, String phoneNumber, String location, String password) {
        this.fullNames = fullNames;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.password = password;
    }

    @NotNull
    private String fullNames;

    @NotNull
    private String email;
    @NotFound
    private String phoneNumber;
    @NotFound
    private String location;
    @NotFound
    private String password;
}
