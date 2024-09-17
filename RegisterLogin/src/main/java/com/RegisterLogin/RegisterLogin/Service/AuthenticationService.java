package com.RegisterLogin.RegisterLogin.Service;

import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public boolean authenticate(AuthenticationRequest request);
}
