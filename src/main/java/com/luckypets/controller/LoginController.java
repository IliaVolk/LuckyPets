package com.luckypets.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
@Deprecated
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model){
        return "home";
    }

}
