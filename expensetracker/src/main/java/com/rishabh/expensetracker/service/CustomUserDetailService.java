package com.rishabh.expensetracker.service;

import com.rishabh.expensetracker.entity.User;
import com.rishabh.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User exist =  userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(exist.getEmail(),exist.getPassword(),new ArrayList<>());
    }
}
