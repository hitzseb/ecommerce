package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final EmailServiceImpl emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username)));

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("id", user.getId());
        session.setAttribute("name", user.getName());
        session.setAttribute("role", user.getRole().name());

        return user;
    }

    @Override
    public void signUpUser(User user) {
        boolean userExists = repo.findByUsername(user.getUsername()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email already registered");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String verificationToken = UUID.randomUUID().toString();
        user.setPassword(encodedPassword);
        user.setVerificationToken(verificationToken);
        repo.save(user);

        String verificationLink = "http://localhost:8080/confirmation?token=" + verificationToken;

        emailService.sendVerificationEmail(user.getUsername(), verificationLink);
    }

    @Override
    public Optional<User> getUserByToken(String verificationToken) {
        return repo.findByVerificationToken(verificationToken);
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        repo.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void updateUser(User user) {
        repo.save(user);
    }
}
