package com.rishabh.expensetracker.service;

import com.rishabh.expensetracker.entity.User;
import com.rishabh.expensetracker.entity.UserModel;

public interface UserService {

    User createUser(UserModel userModel);
}
