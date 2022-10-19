package com.example.bgame.service.user;

import com.example.bgame.model.internal.user.User;
import com.example.bgame.model.internal.user.dto.UserProfileInfoRequest;

public interface UserDataService {
    void addProfileDetails(UserProfileInfoRequest request);
    User getLoggedUser();
}
