package com.example.bgame.api.auth;


import com.example.bgame.model.internal.user.User;
import com.example.bgame.payload.request.LoginRequest;
import com.example.bgame.payload.request.SignupRequest;
import com.example.bgame.payload.response.JwtResponse;
import com.example.bgame.service.user.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authenticationService.authenticateAndReturnJwt(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = authenticationService.registerUser(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail(), signUpRequest.getRole());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
