package com.RegisterLogin.RegisterLogin.Service;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.dto.request.UserCreatation;
import com.RegisterLogin.RegisterLogin.dto.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    User userCreate(UserCreatation userCreatation);

    User getUserById(String userId);

    User updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
