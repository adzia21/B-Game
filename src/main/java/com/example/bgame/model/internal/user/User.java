package com.example.bgame.model.internal.user;

import com.example.bgame.model.internal.bgame.EBoardGameTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_userId"),
            inverseJoinColumns = @JoinColumn(name = "role_roleId"))
    private Set<Role> roles = new HashSet<>();

    @Size(min = 10, max = 380)
    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<EBoardGameTypes> boardGameTypes;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


}
