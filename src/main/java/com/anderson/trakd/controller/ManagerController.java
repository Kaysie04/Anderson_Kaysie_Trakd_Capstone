package com.anderson.trakd.controller;

import com.anderson.trakd.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;


    // GET ROUTES

    // Render page to display all 5 managers
    @GetMapping("/allManagers")
    public String getAllManagers(Model model){
        model.addAttribute("manager", managerRepository.findAll());
        managerRepository.findAll();
        return "all_managers";
    }
}
