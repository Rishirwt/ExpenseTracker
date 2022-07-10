package com.rishabh.expensetracker.service;

import com.rishabh.expensetracker.entity.User;
import com.rishabh.expensetracker.entity.UserModel;
import com.rishabh.expensetracker.exception.ItemAlreadyExistsException;
import com.rishabh.expensetracker.exception.ResourceNotFoundException;
import com.rishabh.expensetracker.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(UserModel userModel) {

        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("This email is already registered");
        }
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User readUser() {
        Long id = getLoggedInUser().getId();
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User id is invalid"));

    }

    @Override
    public User updateUser(UserModel user) {


        User existing = readUser();

        existing.setName(user.getName()!=null ? user.getName() : existing.getName());
        existing.setEmail(user.getEmail()!=null ? user.getEmail() : existing.getEmail());
        existing.setAge(user.getAge()!=null ? user.getAge() : existing.getAge());
        existing.setPassword(user.getPassword()!=null ? passwordEncoder.encode(user.getPassword()) : existing.getPassword());

        userRepository.save(existing);

        return existing;
    }

    @Override
    public void deleteUser() {
        Long id = readUser().getId();
        userRepository.deleteById(id);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User uu= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found"));

        return uu;
    }
}
