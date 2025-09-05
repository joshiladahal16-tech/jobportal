package com.project.jobportal5.controller;

import com.project.jobportal5.entity.User;
import com.project.jobportal5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("qualifications", List.of("Graduate", "Postgraduate", "PhD"));
        model.addAttribute("experienceLevels", List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
        return "register"; // matches register.html
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("userForm") User userForm, Model model) {
        // save user (service/repository logic goes here)
        model.addAttribute("successMessage", "Signup successful! Please log in.");
        return "login"; // must have login.html
    }
}
