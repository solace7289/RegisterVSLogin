package com.RegisterLogin.RegisterLogin.Service;

import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
