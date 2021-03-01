package ru.ivanov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String getHello(@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           Model model){

        model.addAttribute("message", "Hello, " + name + " " + surname);


        return "hello_world";
    }
}
