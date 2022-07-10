package com.rishabh.expensetracker.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserModel {

    @NotBlank(message="name should not be empty")
    private String name;

    @NotBlank(message = "email should not be empty")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be 5 characters long")
    private String password;

    private Long age = 0L;

}
