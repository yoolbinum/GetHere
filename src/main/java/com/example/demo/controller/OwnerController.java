package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.TransferService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    private final String modelDir = "model/";

    @RequestMapping("/calendar")
    public String viewCalendar(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("employees", userService.findEmployees());
        return modelDir + "calendar/edit";
    }

    @RequestMapping("/send")
    public String send(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("employees", userService.findEmployees());
        return modelDir + "payment/send";
    }

    @RequestMapping("/sent")
    public String sendNone(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("employees", new HashSet());
        return modelDir + "payment/send";
    }

    @RequestMapping("/history")
    public String history(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("transfers", transferService.findTransfers());

        return modelDir + "payment/history";
    }

    @RequestMapping("/debit")
    public String debitCard(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);

        return modelDir + "payment/debit";
    }


}
