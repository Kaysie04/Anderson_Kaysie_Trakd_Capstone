package com.anderson.trakd.controller;

import com.anderson.trakd.model.User;
import com.anderson.trakd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	// handler method to handle home page request
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
	// handler method to handle user registration form request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		// create model object to store form data
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/register";
        }

        userService.save(user);
        return "success";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
    	List<User> users = userService.findAll();
    	model.addAttribute("users", users);
    	return "users";
    }
    
    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    
}
