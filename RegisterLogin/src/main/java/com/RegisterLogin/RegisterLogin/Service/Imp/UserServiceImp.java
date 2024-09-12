package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
