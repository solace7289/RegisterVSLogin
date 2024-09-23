package com.RegisterLogin.RegisterLogin.Service.Imp;

import com.RegisterLogin.RegisterLogin.Exception.AppException;
import com.RegisterLogin.RegisterLogin.Exception.ErrorCode;
import com.RegisterLogin.RegisterLogin.Repository.UserRepository;
import com.RegisterLogin.RegisterLogin.Service.AuthenticationService;
import com.RegisterLogin.RegisterLogin.dto.request.AuthenticationRequest;
import com.RegisterLogin.RegisterLogin.dto.request.IntrospectRequest;
import com.RegisterLogin.RegisterLogin.dto.response.AuthenticationResponse;
import com.RegisterLogin.RegisterLogin.dto.response.IntrospectResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

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

    //introspect token
    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {

        //get token
        var token = request.getToken();

        //verifier
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());


        SignedJWT signedJWT = SignedJWT.parse(token);

        //check token expired?
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }


}
