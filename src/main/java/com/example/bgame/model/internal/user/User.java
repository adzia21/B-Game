package com.example.bgame.model.internal.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_userId"),
            inverseJoinColumns = @JoinColumn(name = "role_roleId"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String password, String email, String phone, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }
}
