package com.RegisterLogin.RegisterLogin.Controllers;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import com.RegisterLogin.RegisterLogin.dto.response.ApiResponse;
import com.RegisterLogin.RegisterLogin.dto.request.UserCreationRequest;
import com.RegisterLogin.RegisterLogin.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.userCreate(request));

        return apiResponse;
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
