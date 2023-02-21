package com.anderson.trakd.controller;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import com.anderson.trakd.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    // LOGIN ROUTES
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/userhome";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        // Check for invalid email
        User sessionUser = userRepository.findByEmail(email);
        if (sessionUser == null) {
            model.addAttribute("errorMessage", "Invalid email");
            return "login";
        }
        // Check for invalid password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, sessionUser.getPassword())) {
            model.addAttribute("errorMessage", "Invalid password");
            return "login";
        }
        session.setAttribute("user", email);
        //sessionUser.setLoggedIn(true);
        return "redirect:/userhome";
    }

    // LOGOUT ROUTE
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


    // SIGNUP ROUTES
    @GetMapping("/signup")
    public String renderSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, HttpSession session, Model model) throws Exception {

        User userExists = userRepository.findByEmail(user.getEmail());
        // if email already exists error will pop up
        if (userExists != null) {
            model.addAttribute("error", "Email is already taken");
            return "signup";
        }
        try {
            // encrypt entered password
            int strength = 10;
            BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bcryptEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            // save user information
            userService.createUser(user);
            session.setAttribute("user", user.getEmail());
            return "redirect:/userhome";

        } catch (Exception e) {
            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "signup";
        }

    }

    @GetMapping("/")
    public String renderHomepage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("email", email);
        }
        return "home";
    }


    // USERHOME ROUTES
    @GetMapping("/userhome")
    public String renderUserHome(HttpSession session, Model model) {
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("userEmail", email);
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }

    // DELETE USER ACCOUNT

    @GetMapping("/delete")
    public String renderDeleteForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("userEmail", email);
            return "delete";
        } else {
            return "redirect:/login";
        }
    }




    @PostMapping("/delete")
    public String deleteAccount(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User sessionUser = userRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (sessionUser != null && encoder.matches(password, sessionUser.getPassword()) && email.equals(sessionUser.getEmail())) {
            userRepository.delete(sessionUser); // delete user account
            session.invalidate();
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "delete";
        }
    }

}
