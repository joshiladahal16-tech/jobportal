package com.project.jobportal5.controller;

import com.project.jobportal5.entity.User;
import com.project.jobportal5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/dashboard";
        }
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute User user, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingUser = userService.findByUsername(username).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());

            userService.save(existingUser);
            model.addAttribute("success", "Profile updated successfully!");
            model.addAttribute("user", existingUser);
        }
        return "user/profile";
    }
}