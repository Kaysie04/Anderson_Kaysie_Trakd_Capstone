package com.anderson.trakd.controller;

import com.anderson.trakd.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DeptController {

    @Autowired
    private DeptRepository deptRepository;

    @GetMapping("/allDepts")
    public String getAllDepts(Model model){
        model.addAttribute("dept", deptRepository.findAll());
        deptRepository.findAll();
        return "all_depts";
    }

}
