package com.hitzseb.ecommerce.controller.api;

import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = service.findAllUsers();
        return ResponseEntity.ok(userList);
    }
}
