package com.example.bgame.payload.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Setter
@Getter
public class LoginRequest {
    @NonNull
    @NotEmpty
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NonNull
    @NotEmpty
    @NotBlank
    @Size(min = 4, max = 20)
    private String password;
}
