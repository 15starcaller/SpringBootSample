package com.cko.sampleSpringProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/")
    public String showMainPage() {
        return "mainPage";
    }

    @GetMapping("/game")
    public String showGamePage() {
        return "game";
    }
    @GetMapping("/test")
    public String testing(@RequestParam String name, @RequestParam String mood) {
    System.out.println(name);
        System.out.println(mood);
        return "test";
    }

}
