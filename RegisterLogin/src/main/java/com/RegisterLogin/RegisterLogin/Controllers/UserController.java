package com.RegisterLogin.RegisterLogin.Controllers;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import com.RegisterLogin.RegisterLogin.dto.request.UserCreatation;
import com.RegisterLogin.RegisterLogin.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public User createUser(@RequestBody UserCreatation request){
        return userService.userCreate(request);
    }

    //get all users in database
    @GetMapping()
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    //get user by userId
    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    //update user
    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    //Delete user
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User have been deleted";
    }


}
