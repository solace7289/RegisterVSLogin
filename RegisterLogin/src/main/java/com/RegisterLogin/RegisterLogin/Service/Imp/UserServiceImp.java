package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import com.RegisterLogin.RegisterLogin.dto.request.UserCreatation;
import com.RegisterLogin.RegisterLogin.dto.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User userCreate(UserCreatation request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setGender(request.isGender());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());
        user.setImg(request.getImg());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.isGender());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());
        user.setImg(request.getImg());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
