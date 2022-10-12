package com.example.bgame.repository;

import com.example.bgame.model.internal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName(String name);

    Boolean existsByEmail(String name);

    Boolean existsByName(String name);
}
