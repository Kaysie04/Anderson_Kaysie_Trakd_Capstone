package com.anderson.trakd.controller;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NHCompanyCredentialsController {

    private final NHCompanyCredentialsService nhCompanyCredentialsService;


    public NHCompanyCredentialsController(NHCompanyCredentialsService nhCompanyCredentialsService) {
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
    }

    // GET ROUTES

    //Render generate company credentials form
    // when you click submit it hits the post route /generate-credentials
    @GetMapping("/generate-credentials")
    public String renderGenerateCredentials(HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            return "generate_credentials";
        }else {
            return "redirect:/login";
        }
    }


    // Render list of company credentials in the nhcompany table
    @GetMapping("/all-credentials")
    public String getAllCredentials(HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("allCredentials", nhCompanyCredentialsService.getAllNHCredentials());
            nhCompanyCredentialsService.getAllNHCredentials();
            return "all_credentials";
        }else {
            return "redirect:/login";
        }
    }
}
