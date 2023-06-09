package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    void signUpUser(User user);
    Optional<User> getUserByToken(String verificationToken);
    void enableUser(User user);
    Optional<User> findUserById(Long id);
    void updateUser(User user);
}