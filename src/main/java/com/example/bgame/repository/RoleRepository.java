package com.example.bgame.repository;

import com.example.bgame.model.internal.user.ERole;
import com.example.bgame.model.internal.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
