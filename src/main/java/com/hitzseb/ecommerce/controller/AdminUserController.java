package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public String showUserList(Model model, HttpSession session) {
        List<User> userList = userService.findAllUsers(session);
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userList", userList);
        return "adm-user-list";
    }

}
