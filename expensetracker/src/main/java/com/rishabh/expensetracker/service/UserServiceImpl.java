package com.rishabh.expensetracker.service;

import com.rishabh.expensetracker.entity.User;
import com.rishabh.expensetracker.entity.UserModel;
import com.rishabh.expensetracker.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(UserModel userModel) {
        User user = new User();
        BeanUtils.copyProperties(userModel,user);

        return userRepository.save(user);
    }
}
