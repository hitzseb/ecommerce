package com.hitzseb.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/**")
    public String redirect() {
        return "redirect:/";
    }
}
