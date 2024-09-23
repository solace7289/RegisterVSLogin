package com.RegisterLogin.RegisterLogin.Controllers;


import com.RegisterLogin.RegisterLogin.Service.AuthenticationService;
import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.request.IntrospectRequest;
import com.RegisterLogin.RegisterLogin.dto.response.ApiResponse;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import com.RegisterLogin.RegisterLogin.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    //instrospect token
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        //create var result of request
        var result = authenticationService.introspect(request);

        //return result as a APIResponse object
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }




}
