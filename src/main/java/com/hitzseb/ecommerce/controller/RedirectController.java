package com.hitzseb.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @RequestMapping("/**")
    public RedirectView redirectToHome() {
        return new RedirectView("/", true);
    }
}
