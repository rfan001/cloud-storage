package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.User;
import com.yowbuy.cloudstorage.sevice.HashService;
import com.yowbuy.cloudstorage.sevice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String visitSignup(){
        return "signup";
    }
    @PostMapping
    public String signup(@ModelAttribute User user, Model model){
        String signupError = null;
        if(!userService.usernameIsValid(user.getUsername())){
            signupError = "The username already exists.";
        }
        if(signupError == null){
            int rowAdded = userService.createUser(user);
            if(rowAdded < 0){
                signupError = "There was an error signing you up. Please try again.";
            }
        }
        if(signupError == null){
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
