package com.luckypets.controller;


import com.luckypets.entity.User;
import com.luckypets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String createNewProfile(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveUserFromForm(@Valid User user,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//errors checking
            return "user/edit";
        }

        userService.saveUser(user);

        return "redirect:/user/" + user.getLogin();
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String login,
                                     Model model) {
        model.addAttribute("user", userService.getUser(login));
        return "user/profile";
    }
}
