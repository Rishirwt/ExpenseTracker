package com.rishabh.expensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String jwtToken;
}
