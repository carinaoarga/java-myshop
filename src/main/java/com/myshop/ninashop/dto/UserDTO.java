package com.myshop.ninashop.dto;


import com.myshop.ninashop.model.Role;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private String lastname;

    private String firstname;

    private String email;

    private String password;

//    private String confirmPassword;

//    private String street;

//    private String number;

//    private String city;

//    private String zip;


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
