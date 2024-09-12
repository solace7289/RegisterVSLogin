package com.RegisterLogin.RegisterLogin.Controllers;

import com.RegisterLogin.RegisterLogin.Entities.User;
import com.RegisterLogin.RegisterLogin.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody User user,
                                     BindingResult result){
        Map<String, Object> status = new HashMap<>();

        if (result.hasErrors()){
            status.put("status", "400");
            status.put("message", "fail!!!!!!!!");
            List<String> errorMessage = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            status.put("error", errorMessage);
        }else {
            status.put("status", "200");

            //add new user
            userService.saveUser(user);

            status.put("message", "user have been register");

        }

        return  status;
    }

}
