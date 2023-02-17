package com.anderson.trakd.controller;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import com.anderson.trakd.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {



  private final UserRepository userRepository;
  private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    // GET ROUTES

    // login
    @GetMapping("/login")
    public String renderLogin(Model model, HttpServletRequest request){
        if(request.getSession(false) !=null){
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    // signup
    @GetMapping("/signup")
    public String renderSignup(Model model, HttpServletRequest request){
        if(request.getSession(false) !=null){
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "signup";
    }

    // logout
    @GetMapping("/logout")
    public String renderLogout(HttpServletRequest request){
        if(request.getSession(false) !=null){
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

    // homepage
    @GetMapping("/")
    public String renderHomepage(Model model, HttpServletRequest request){
        User currentUser = new User();
        if(request.getSession(false)!=null){
            currentUser = (User) request.getSession().getAttribute("SESSION_USER");
            model.addAttribute("loggedIn"); currentUser.isLoggedIn();
        } else {
            model.addAttribute("loggedIn", false);
        }

        return"homepage";
    }

    @GetMapping("/userhome")
    public String renderUserHome(){
        return "userhome";
    }

    // POST ROUTES

    // signup
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model, HttpServletRequest request){
        if((user.getEmail().equals(null) || user.getEmail().isEmpty())
                || (user.getPassword().equals(null) || user.getPassword().isEmpty())
                || (user.getName().equals(null) || user.getName().isEmpty())){

            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "signup";
        }
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userService.createUser(user);
        }catch (Exception e){
            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "redirect:/login";
        }

        return "redirect:/userhome";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest request) throws Exception{
        User currentUser = userRepository.findByEmail(email);

        if(currentUser == null){
            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "error";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, currentUser.getPassword())){
            String error = "Invalid input was entered!";
            model.addAttribute("error", error);
            return "error";
        } else {
            currentUser.setLoggedIn(true);
            request.getSession().setAttribute("SESSION_USER", currentUser);
            return "redirect:/userhome";
        }

    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
//        if((user.getEmail().equals(null) || user.getEmail().isEmpty())
//                || (user.getPassword().equals(null) || user.getPassword().isEmpty())
//                || (user.getName().equals(null) || user.getName().isEmpty())){
//
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "login";
//        }
//        User currentUser = userRepository.findByEmail(user.getEmail());
//        currentUser.setLoggedIn(true);
//        request.getSession().setAttribute("SESSION_USER", currentUser);
//        return "redirect:/userhome";
//
//    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
//        if((user.getEmail().equals(null) || user.getEmail().isEmpty())
//                || (user.getPassword().equals(null) || user.getPassword().isEmpty())
//                || (user.getName().equals(null) || user.getName().isEmpty())){
//
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "signup";
//        }
//        try {
//            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
//            userService.createUser(user);
//        }catch (Exception e){
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "redirect:/login";
//        }
//
//        return "redirect:/userhome";
//    }


}

