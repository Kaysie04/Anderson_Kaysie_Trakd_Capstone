package com.anderson.trakd.controller;

import com.anderson.trakd.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/allDepts")
    public String getAllDepts(Model model){
        model.addAttribute("dept", deptService.getAllDepts());
        deptService.getAllDepts();
        return "all_depts";
    }

}
