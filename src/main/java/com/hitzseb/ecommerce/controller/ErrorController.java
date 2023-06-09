package com.hitzseb.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ErrorController {
    public String handleError(Model model, @ModelAttribute HttpSession session) {
        return "error";
    }
}

