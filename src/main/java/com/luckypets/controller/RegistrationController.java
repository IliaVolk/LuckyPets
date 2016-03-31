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

@RequestMapping("/registration")
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String createNewProfile(Model model) {
        model.addAttribute("user",
                new User());
        return "registration/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveUserFromForm(@Valid User user,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//errors checking
            return "registration/edit";
        }

        userService.saveUser(user);

        return "redirect:/registration/" + user.getLogin();
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String login,
                                     Model model) {
        model.addAttribute("user", userService.getUser(login));
        return "registration/hello";
    }
}
