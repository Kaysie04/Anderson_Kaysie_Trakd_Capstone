package com.anderson.trakd.controller;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NHCompanyCredentialsController {

    private final NHCompanyCredentialsService nhCompanyCredentialsService;



    public NHCompanyCredentialsController(NHCompanyCredentialsService nhCompanyCredentialsService) {
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
    }


    //Render the generate company credentials for when you click submit it hits the post route /generate-credentials

    @GetMapping("/generate-credentials")
    public String renderGenerateCredentials(){
        return "generate-credentials";
    }

    @GetMapping("/all-credentials")
    public String getAllCredentials(Model model){
        model.addAttribute("allCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        nhCompanyCredentialsService.getAllNHCredentials();
        return "all-credentials";
    }

}
