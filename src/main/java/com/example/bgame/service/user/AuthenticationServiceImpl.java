package com.example.bgame.service.user;

import com.example.bgame.exception.EmailAlreadyTakenException;
import com.example.bgame.exception.RoleNotFoundException;
import com.example.bgame.exception.UsernameTakenException;
import com.example.bgame.jwt.JwtUtils;
import com.example.bgame.model.internal.user.Role;
import com.example.bgame.model.internal.user.User;
import com.example.bgame.payload.response.JwtResponse;
import com.example.bgame.repository.RoleRepository;
import com.example.bgame.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.bgame.model.internal.user.ERole.ADMIN;
import static com.example.bgame.model.internal.user.ERole.USER;

@AllArgsConstructor
@Service
class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    @Override
    public JwtResponse authenticateAndReturnJwt(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return new JwtResponse(jwt, "Bearer", userDetails.getId(), userDetails.getName(), userDetails.getEmail(), roles);
    }

    @Override
    public User registerUser(String name, String password, String email, Set<String> requestedRoles) {
        if (userRepository.existsByName(name)) {
            throw new UsernameTakenException(name);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyTakenException(email);
        }

        User user = new User(name, encoder.encode(password), email);
        Set<Role> roles = getRoles(requestedRoles);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    private Set<Role> getRoles(Set<String> requestedRoles) {
        Set<Role> roles = new HashSet<>();

        if (requestedRoles == null) {
            Role userRole = roleRepository.findByName(USER).orElseThrow(RoleNotFoundException::new);
            roles.add(userRole);
        } else {
            requestedRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ADMIN).orElseThrow(RoleNotFoundException::new);
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(USER).orElseThrow(RoleNotFoundException::new);
                    roles.add(userRole);
                }
            });
        }
        return roles;
    }
}
