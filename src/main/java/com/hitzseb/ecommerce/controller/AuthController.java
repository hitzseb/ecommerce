package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@ModelAttribute User user, @RequestParam String confirmPassword, BindingResult bindingResult, Model model) {
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.password", "Las contraseñas no coincide.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }
        service.signUpUser(user);
        model.addAttribute("message", "Gracias por registrarte. Hemos enviado un correo a su dirección de email.");
        return "greeting";
    }

    @GetMapping("/confirmation")
    public String confirmRegistration(@RequestParam("token") String verificationToken, Model model) {
        Optional<User> user = service.getUserByToken(verificationToken);
        if (!user.isPresent()) {
            model.addAttribute("errorMessage", "Invalid verification token");
            return "error";
        }
        service.enableUser(user.get());
        model.addAttribute("message", "Su cuenta ya se encuentra habilitada.");
        return "greeting";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "redirect:";
    }

}
