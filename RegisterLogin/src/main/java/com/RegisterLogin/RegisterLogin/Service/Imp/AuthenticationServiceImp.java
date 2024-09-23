package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Exception.AppException;
import com.RegisterLogin.RegisterLogin.Exception.ErrorCode;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.AuthenticationService;
import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    //key
    protected static final String SIGNER_KEY = "CDHqPBzvf/cd16lcRMOGzLh3d0jFYam6J+cSO1luuzHgl/4KcutQLJes88De76A5";

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //get user information
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST));

        //create password encoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        //return result of matching
        boolean authenticated =  passwordEncoder.matches(request.getPassword(),
                user.getPassword());

        //check
        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        //generate token
        var token = generateToken(request.getUsername());

        //return token and authenticated
        return AuthenticationResponse.builder()
                .token(token)
                .Authenticated(true)
                .build();

    }

    //generate a token
    private String generateToken(String username){

        //set header (algorithm of token)
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //set jwt claim set
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("G39")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customeClaim", "some thing in herer")
                .build();

        //set jwt payload: and change it into JSONObject
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        //create a jws object with header and payload
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //signer <need signer key>
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            //return with serialize
            return jwsObject.serialize();
        }catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }


}
