package com.anderson.trakd.controller;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.DeptRepository;
import com.anderson.trakd.repository.ManagerRepository;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import com.anderson.trakd.repository.NHPersonalInformationRepository;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import com.anderson.trakd.service.NHPersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NHPersonalInformationController {


    private final NHPersonalInformationService nhPersonalInformationService;

    private final NHCompanyCredentialsService nhCompanyCredentialsService;
    private final DeptRepository deptRepository;
    private final ManagerRepository managerRepository;

    private final NHCompanyCredentialsRepository nhCompanyCredentialsRepository;
    @Autowired
    private NHPersonalInformationRepository nhPersonalInformationRepository;




    public NHPersonalInformationController(NHPersonalInformationService nhPersonalInformationService,
                                           NHCompanyCredentialsService nhCompanyCredentialsService,
                                           DeptRepository deptRepository,
                                           ManagerRepository managerRepository,
                                           NHCompanyCredentialsRepository nhCompanyCredentialsRepository)
    {
        this.nhPersonalInformationService = nhPersonalInformationService;
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
        this.deptRepository = deptRepository;
        this.managerRepository = managerRepository;
        this.nhCompanyCredentialsRepository = nhCompanyCredentialsRepository;
    }

    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all-newhires-personal";
        // I need to show associated employee Id's for input field next to table
    }


    @GetMapping("/create-newhire")
    public String renderCreateNewHire(Model model){
        model.addAttribute("nhPersonal", new NHPersonalInformation());
        model.addAttribute("dept", deptRepository.findAll());
        model.addAttribute("manager", managerRepository.findAll());
        model.addAttribute("companyCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        return "create-newhire";
    }
    // need to create custom query so you know who is head of what dept

    @PostMapping("/create-newhire")
    public String createNewHire(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonalInformation) {
        nhPersonalInformationService.createNHPersonal(nhPersonalInformation);
//        return "redirect:/employee";
        return"success";
    }

}












