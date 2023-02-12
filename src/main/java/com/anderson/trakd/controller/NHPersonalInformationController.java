package com.anderson.trakd.controller;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import com.anderson.trakd.service.NHPersonalInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NHPersonalInformationController {

    private final NHPersonalInformationService nhPersonalInformationService;
    private final NHCompanyCredentialsService nhCompanyCredentialsService;


    public NHPersonalInformationController(NHPersonalInformationService nhPersonalInformationService, NHCompanyCredentialsService nhCompanyCredentialsService) {
        this.nhPersonalInformationService = nhPersonalInformationService;
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
    }

    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(Model model){
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all-newhires-personal";
    }

    @GetMapping("/add-company-credentials")
        public String addCompanyCredentials(Model model){
        model.addAttribute("allNewHires", nhPersonalInformationService.getAllNHPersonal());
        model.addAttribute("allCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        nhPersonalInformationService.getAllNHPersonal();
        nhCompanyCredentialsService.getAllNHCredentials();
        return "add-company-credentials";
        }
}
