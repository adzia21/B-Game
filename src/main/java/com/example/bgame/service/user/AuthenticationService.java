package com.example.bgame.service.user;

import com.example.bgame.model.internal.user.User;
import com.example.bgame.payload.response.JwtResponse;

import java.util.Set;

public interface AuthenticationService {
    JwtResponse authenticateAndReturnJwt(String username, String password);

    User registerUser(String name, String password, String email, Set<String> requestedRoles);
}
