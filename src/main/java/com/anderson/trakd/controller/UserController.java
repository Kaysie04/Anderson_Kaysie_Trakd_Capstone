//package com.anderson.trakd.controller;
//
//import com.anderson.trakd.model.User;
//import com.anderson.trakd.repository.UserRepository;
//import com.anderson.trakd.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.security.SecureRandom;
//
//@Controller
//public class UserController {
//
//
//
//  private final UserRepository userRepository;
//  private final UserService userService;
//
//    public UserController(UserRepository userRepository, UserService userService) {
//        this.userRepository = userRepository;
//        this.userService = userService;
//    }
//
//
//    // GET ROUTES
//
//    // login
//    @GetMapping("/login")
//    public String renderLogin(Model model, HttpServletRequest request){
//        if(request.getSession(false) !=null){
//            return "redirect:/";
//        }
//        model.addAttribute("user", new User());
//        return "login";
//    }
//
//    // signup
//    @GetMapping("/signup")
//    public String renderSignup(Model model, HttpServletRequest request){
//        if(request.getSession(false) !=null){
//            return "redirect:/";
//        }
//        model.addAttribute("user", new User());
//        return "signup";
//    }
//
//    // logout
//    @GetMapping("/logout")
//    public String renderLogout(HttpServletRequest request){
//        if(request.getSession(false) !=null){
//            request.getSession().invalidate();
//        }
//        return "redirect:/login";
//    }
//
//    // homepage
////    @GetMapping("/")
////    public String renderHomepage(Model model, HttpServletRequest request){
////        User sessionUser = new User();
////        if(request.getSession(false)!=null){
////            sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
////            model.addAttribute("loggedIn"); sessionUser.isLoggedIn();
////        } else {
////            model.addAttribute("loggedIn", false);
////        }
////
////        return"homepage";
////    }
//
////    @GetMapping("/")
////    public String renderHomepage(HttpSession session, Model model) {
////        String email = (String) session.getAttribute("user");
////        if (email != null) {
////            model.addAttribute("email", email);
////        }
////        return "homepage";
////    }
//
//
//    @GetMapping("/userhome")
//    public String renderUserHome(HttpSession session) {
//        String email = (String) session.getAttribute("user");
//        if (email != null) {
//            ResponseEntity.ok();
//            return "userhome";
//        } else {
//            return "error";
//        }
//    }
//
//    // POST ROUTES
//
//    // signup
//    @PostMapping("/signup")
//    public String signup(@ModelAttribute User user, Model model, HttpServletRequest request){
//
//        if((user.getEmail().equals(null) || user.getEmail().isEmpty())
//                || (user.getPassword().equals(null) || user.getPassword().isEmpty())
//                || (user.getName().equals(null) || user.getName().isEmpty())){
//
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "signup";
//        }
//        try {
//            int strength = 10;
//            BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
//            String encodedPassword = bcryptEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);
//            userService.createUser(user);
//        }catch (Exception e){
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "redirect:/login";
//        }
//
//        return "redirect:/userhome";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session){
//        User sessionUser = userRepository.findByEmail(email);
//
//        if(sessionUser == null){
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "error";
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(!encoder.matches(password, sessionUser.getPassword())){
//            String error = "Invalid input was entered!";
//            model.addAttribute("error", error);
//            return "error";
//        } else {
//            sessionUser.setLoggedIn(true);
//            //request.getSession().setAttribute("SESSION_USER", sessionUser);
//            session.setAttribute("SESSION_USER", sessionUser.getEmail());
//            return "redirect:/userhome";
//        }
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {
////        try {
////            Authentication authentication = authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////            session.setAttribute("user", user.getUsername());
////            return ResponseEntity.ok("Login successful");
////        } catch (AuthenticationException e) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
////        }
////    }
//}
//
