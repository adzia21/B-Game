package com.example.bgame.service.user;

import com.example.bgame.exception.UserNotFoundException;
import com.example.bgame.model.internal.user.User;
import com.example.bgame.model.internal.user.dto.UserProfileInfoRequest;
import com.example.bgame.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDataService{
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException(username)); //TODO CHANGE
        return UserDetailsImpl.build(user);
    }

    public UserDetails findUserById(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserDetailsImpl.build(user);
    }

    @Override
    public void addProfileDetails(UserProfileInfoRequest request) {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId));
        if (request.getDescription() != null) {
            user.setDescription(request.getDescription());
        }
        if (request.getBoardGameTypes() != null) {
            user.setBoardGameTypes(request.getBoardGameTypes());
        }

        userRepository.save(user);
    }

    @Override
    public User getLoggedUser() {
        Long loggedUserId = getLoggedUserId();
        return userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId));
    }

    private static Long getLoggedUserId() {
        UserDetailsImpl loggedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedUser.getId();
    }

}
