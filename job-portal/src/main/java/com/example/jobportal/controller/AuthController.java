package com.example.jobportal.controller;

import com.example.jobportal.entity.User;
import com.example.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username is already taken");
            return "register";
        }
        
        // ensure default role is set if none provided, though template should provide
        if (user.getRole() == null) {
            user.setRole(User.Role.STUDENT);
        }
        
        userService.registerUser(user);
        return "redirect:/login?success";
    }
}
