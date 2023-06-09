package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Role;
import com.hitzseb.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByVerificationToken(String verificationToken);
    List<User> findByRoleOrderByName(Role role);
}
