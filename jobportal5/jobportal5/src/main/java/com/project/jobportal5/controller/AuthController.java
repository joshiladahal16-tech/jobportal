package com.project.jobportal5.controller;

import com.project.jobportal5.entity.User;
import com.project.jobportal5.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
        model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
        return "register";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
        model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
        return "register";
    }

    // Legacy/static routes -> redirect to correct MVC endpoints
    @GetMapping("/user-register.html")
    public String legacyUserRegister() {
        return "redirect:/register";
    }

    @GetMapping("/user-login.html")
    public String legacyUserLogin() {
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("userForm") User user,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
            model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
            return "register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Username already exists!");
            model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
            model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
            return "register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "Email already exists!");
            model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
            model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
            return "register";
        }

        try {
            userService.registerUser(user, "ROLE_USER");
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed. Please try again.");
            model.addAttribute("qualifications", java.util.List.of("High School", "Diploma", "Graduate", "Postgraduate", "PhD"));
            model.addAttribute("experienceLevels", java.util.List.of("Fresher", "1-3 years", "3-5 years", "5+ years"));
            return "register";
        }
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        try {
            userService.registerUser(user, "ROLE_USER");
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }
}