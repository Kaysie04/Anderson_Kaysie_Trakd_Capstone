package com.anderson.trakd.controller;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import com.anderson.trakd.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.AuthenticationException;
import java.security.SecureRandom;

@Controller
public class UserController2 {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    // LOGIN ROUTES
    @GetMapping("/login")
    public String renderLogin(){
        return "login2";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){

        try{
            User sessionUser = userRepository.findByEmail(email);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(password, sessionUser.getPassword())) {
                session.setAttribute("user", email);
                return "redirect:/userhome";

            } throw new AuthenticationException();
            } catch (AuthenticationException e){
                model.addAttribute("errorMessage", "Invalid email or password");
                return "login2";
            }
    }

    // LOGOUT ROUTE
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // SIGNUP ROUTES
    @GetMapping("/signup")
    public String renderSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup2";
    }


    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, HttpSession session, Model model) throws Exception {
        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            model.addAttribute("error", "Email is already taken");
            return "signup2";
        }
        try {
        int strength = 10;
            BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bcryptEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.createUser(user);
            session.setAttribute("user", user.getEmail());
            return "redirect:/userhome";

        }catch (Exception e){
            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "redirect:/login";
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
    public String renderUserHome(HttpSession session) {
        String email = (String) session.getAttribute("user");
        if (email != null) {
            ResponseEntity.ok();
            return "dashboard";
        } else {
            return "error";
        }
    }
}
