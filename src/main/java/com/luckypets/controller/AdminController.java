package com.luckypets.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/foradmin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String admin(Model model) {
        return "foradmin";
    }
}
