package com.anderson.trakd.controller;

import com.anderson.trakd.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController (ManagerService managerService){
        this.managerService = managerService;
    }

    @GetMapping("/allManagers")
    public String getAllManagers(Model model){
        model.addAttribute("manager", managerService.getAllManagers());
        managerService.getAllManagers();
        return "all_managers";
    }
}
