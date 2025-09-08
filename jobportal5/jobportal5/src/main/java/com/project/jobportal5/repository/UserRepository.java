package com.project.jobportal5.repository;

import com.project.jobportal5.entity.Role;
import com.project.jobportal5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // Query by the ManyToMany collection "roles"
    List<User> findByRoles_Name(String name);
    List<User> findByRoles(Role role);
}