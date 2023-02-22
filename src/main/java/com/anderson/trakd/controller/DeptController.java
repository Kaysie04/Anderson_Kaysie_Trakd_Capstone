package com.anderson.trakd.controller;

import com.anderson.trakd.repository.DeptRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DeptController {

    @Autowired
    private DeptRepository deptRepository;

    // GET ROUTES

    // Render page to display all 5 departments
    @GetMapping("/allDepts")
    public String getAllDepts(HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("dept", deptRepository.findAll());
            deptRepository.findAll();
            return "all_depts";
        }else {
            return "redirect:/login";
        }
    }

}
