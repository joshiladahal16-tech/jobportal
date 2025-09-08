package com.project.jobportal5.controller;

import com.project.jobportal5.entity.User;
import com.project.jobportal5.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/register", "/signup"})
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    // ✅ Fixed: use "userForm" instead of "user" to match binding
    @GetMapping("/user-register.html")
    public String legacyRegister(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping({"/register", "/signup"})
    public String registerUser(@Valid @ModelAttribute("userForm") User user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", user);
            return "register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            model.addAttribute("userForm", user);
            return "register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            model.addAttribute("userForm", user);
            return "register";
        }

        try {
            userService.registerUser(user, "ROLE_USER");
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            model.addAttribute("userForm", user);
            return "register";
        }
    }
}
