package com.anderson.trakd.controller;


import com.anderson.trakd.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public RedirectView login(@ModelAttribute User user) {
    // validate the user credentials

    // if the credentials are valid
    return new RedirectView("users.html");
  }
}
