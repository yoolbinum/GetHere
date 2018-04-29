package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private UserService userService;
    private final String modelDir = "model/";

    @RequestMapping("/calendar")
    public String viewCalendar(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("employees", userService.findEmployees());
        return modelDir + "calendar/edit";
    }
}
