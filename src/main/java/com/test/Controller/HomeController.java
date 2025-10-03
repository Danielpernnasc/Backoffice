package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("localhost:4200/dashboard")
        public String dashboard() {
            return "Você está logado e esta é a área protegida!";
        }
}
