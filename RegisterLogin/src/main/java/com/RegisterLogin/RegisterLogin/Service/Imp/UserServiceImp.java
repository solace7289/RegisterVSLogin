package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Exception.AppException;
import com.RegisterLogin.RegisterLogin.Exception.ErrorCode;
import com.RegisterLogin.RegisterLogin.Mapper.UserMapper;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import com.RegisterLogin.RegisterLogin.dto.request.UserCreationRequest;
import com.RegisterLogin.RegisterLogin.dto.request.UserUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User userCreate(UserCreationRequest request) {

        //checking username is existed or not
        if (userRepository.existsByUsername(request.getUsername())){
            //if existed -> throw runtime exception
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        //else: create new user
        User user = userMapper.toUser(request);

        //encode password by Bcrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        //pass
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        //return result: user, if not then throw an exception: User not found (call to class exception in package Exception)
        return userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
    }

    @Override
    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        //mapping data
        userMapper.updateUser(user, request);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
