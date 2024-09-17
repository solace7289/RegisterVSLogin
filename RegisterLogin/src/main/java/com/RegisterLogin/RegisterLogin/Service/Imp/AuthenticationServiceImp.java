package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Exception.AppException;
import com.RegisterLogin.RegisterLogin.Exception.ErrorCode;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.AuthenticationService;
import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authenticate(AuthenticationRequest request) {
        //get user information
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST));

        //create password encoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        //return result of matching
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }


}
