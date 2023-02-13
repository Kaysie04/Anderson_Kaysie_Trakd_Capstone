package com.anderson.trakd.controller;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.DeptRepository;
import com.anderson.trakd.repository.ManagerRepository;
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
    @Autowired
    private NHPersonalInformationRepository nhPersonalInformationRepository;




    public NHPersonalInformationController(NHPersonalInformationService nhPersonalInformationService, NHCompanyCredentialsService nhCompanyCredentialsService, DeptRepository deptRepository, ManagerRepository managerRepository) {
        this.nhPersonalInformationService = nhPersonalInformationService;
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
        this.deptRepository = deptRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all-newhires-personal";
    }

    @GetMapping("/add-company-credentials")
    public String renderAddCompanyCredentials(Model model) {
        model.addAttribute("allNewHires", nhPersonalInformationService.getAllNHPersonal());
        model.addAttribute("allCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        nhPersonalInformationService.getAllNHPersonal();
        nhCompanyCredentialsService.getAllNHCredentials();
        return "add-company-credentials";
    }

    @GetMapping("/create-newhire")
    public String renderCreateNewHire(Model model){
        model.addAttribute("nhPersonal", new NHPersonalInformation());
        model.addAttribute("dept", deptRepository.findAll());
        model.addAttribute("manager", managerRepository.findAll());
        return "add-dept-manager";
    }
    // need to create custom query so you know who is head of what dept



    @PostMapping("/create-newhire")
    public String createNewHire(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonalInformation) {
        nhPersonalInformationService.createNHPersonal(nhPersonalInformation);
//        return "redirect:/employee";
        return"success";
    }

//    @PutMapping("/add-company-credentials")
//    public String addCompanyCredetials()

//    @PutMapping("/add-company-credentials")
//    public String addCredentials(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonal, @ModelAttribute("nhCompany")NHCompanyCredentials nhCompany){
//        String employeeId = nhCompany.getEmployeeId();
//        Long nhDbId = nhPersonal.getId();
//        nhPersonalInformationService.addCompanyCredentials(nhDbId, employeeId);
//        return "success";
//    }

    @PutMapping("/add-company-credentials")
    public String addCredentials(@RequestParam(value = "nhPersonalId") Long nhPersonalId,
                                 @RequestParam(value = "nhCompanyId") String nhCompanyId)
    {
        nhPersonalInformationService.addCompanyCredentials(nhPersonalId, nhCompanyId);
        return "success";
    }

}







//    @PutMapping("/add-company-credentials")
//    public String addCredentials(@RequestParam(value = "nhPersonalId") Long nhPersonalId,
//                                 @RequestParam(value = "nhCompanyId") String nhCompanyId)
//    {
//        nhPersonalInformationService.addCompanyCredentials(nhPersonalId, nhCompanyId);
//        return "success";
//    }


//    @PutMapping("/{nhPersonalId}/add-credentials/{nhCompanyId}")
//    public String addCredentials(@PathVariable Long nhPersonalId, @PathVariable String nhCompanyId) {
//        nhPersonalInformationService.addCompanyCredentials(nhPersonalId, nhCompanyId);
//        return "success";
//    }



//    @PostMapping("/add-company-credentials")
//    public String addCompanyCredentials(@ModelAttribute NHPersonalInformation nhPersonalInformation, Model model) {
//        nhPersonalInformationRepository.save(nhPersonalInformation);
////        return "redirect:/employees";
//        return "success";
//    }









