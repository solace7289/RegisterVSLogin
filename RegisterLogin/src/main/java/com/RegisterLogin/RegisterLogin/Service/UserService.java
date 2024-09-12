package com.RegisterLogin.RegisterLogin.Service;

import com.RegisterLogin.RegisterLogin.Entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Iterable<User> getAllUser();

    void saveUser(User user);
}
