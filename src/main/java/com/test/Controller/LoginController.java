package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

     @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html com botão "Login com Google"
    }
}
