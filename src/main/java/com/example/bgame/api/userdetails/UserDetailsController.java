package com.example.bgame.api.userdetails;

import com.example.bgame.model.internal.user.User;
import com.example.bgame.model.internal.user.dto.UserProfileInfoRequest;
import com.example.bgame.service.user.UserDataService;
import com.example.bgame.service.user.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user/details")
public class UserDetailsController {

    private UserDataService userDataService;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PutMapping
    public ResponseEntity<Object> setAdditionalFields(@RequestBody @Valid UserProfileInfoRequest request){
        userDataService.addProfileDetails(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/loggedUser")
    public ResponseEntity<User> getLoggedUser(){
        return new ResponseEntity<>(userDetailsServiceImpl.getLoggedUser(), HttpStatus.OK);
    }
}
