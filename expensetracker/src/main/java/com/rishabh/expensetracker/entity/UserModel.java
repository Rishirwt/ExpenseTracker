package com.rishabh.expensetracker.entity;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserModel {

    private String name;
    private String email;
    private String password;
    private Long age = 0L;

}
