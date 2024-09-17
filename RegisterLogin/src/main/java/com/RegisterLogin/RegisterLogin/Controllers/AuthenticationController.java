package com.RegisterLogin.RegisterLogin.Controllers;


import com.RegisterLogin.RegisterLogin.Service.AuthenticationService;
import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.response.ApiResponse;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .Authenticated(result)
                        .build())
                .build();
    }

}
