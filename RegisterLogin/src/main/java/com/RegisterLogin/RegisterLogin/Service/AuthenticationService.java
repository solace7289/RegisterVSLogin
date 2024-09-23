package com.RegisterLogin.RegisterLogin.Service;

import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.request.IntrospectRequest;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import com.RegisterLogin.RegisterLogin.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface AuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
