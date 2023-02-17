package com.anderson.trakd.controller;
import com.anderson.trakd.model.Dept;
import com.anderson.trakd.model.Manager;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.DeptRepository;
import com.anderson.trakd.repository.ManagerRepository;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import com.anderson.trakd.service.NHPersonalInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NHPersonalInformationController {


    private final NHPersonalInformationService nhPersonalInformationService;
    private final NHCompanyCredentialsService nhCompanyCredentialsService;
    private final DeptRepository deptRepository;
    private final ManagerRepository managerRepository;
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
    }

    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all_newhires";
    }

    @GetMapping("/all-newhires")
    public String getAllNewHires(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all_newhires_general";
    }


    @GetMapping("/create-newhire")
    public String renderCreateNewHire(Model model){
        model.addAttribute("nhPersonal", new NHPersonalInformation());
        model.addAttribute("dept", deptRepository.findAll());
        model.addAttribute("manager", managerRepository.findAll());
        model.addAttribute("companyCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        return "create_newhire";
    }


    @PostMapping("/create-newhire")
    public String createNewHire(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonalInformation) {
        nhPersonalInformationService.createNHPersonal(nhPersonalInformation);
//        return "redirect:/employee";
        return"success";
    }

    @GetMapping("/newhires-by-dept")
    public String getAllNHByDeptId(@RequestParam("deptId") Long deptId, Model model){
        List<NHPersonalInformation> nhPersonalList = nhPersonalInformationService.getNHPersonalByDeptId(deptId);
        Dept deptName = deptRepository.getReferenceById(deptId);
        model.addAttribute("newhires", nhPersonalList );
        model.addAttribute("dept", deptName);
        return "all_newhires_by_dept";
    }

    @GetMapping("/newhires-by-manager")
    public String getAllNHByManagerId(@RequestParam("managerId") Long managerId, Model model){
        List<NHPersonalInformation> nhPersonalList = nhPersonalInformationService.getNHPersonalByManagerId(managerId);
        Manager managerName = managerRepository.getReferenceById(managerId);
        model.addAttribute("newhires", nhPersonalList );
        model.addAttribute("manager", managerName);
        return "all_newhires_by_manager";
    }

    @GetMapping("/allTitles")
    public String renderAllTitles(){
        return "all_titles";
    }

    @GetMapping("/newhires-by-title")
    public String getNHByJob(@RequestParam("jobtitle")String jobTitle, Model model){
        List<NHPersonalInformation> nhPersonalList = nhPersonalInformationService.getNHByJobTitle(jobTitle);
        model.addAttribute("newhires", nhPersonalList);
        return "all_newhires_by_title";
    }

    @GetMapping("/newhire-by-id-company")
    public String getNHById(@RequestParam("nhId") Long nhId, Model model){
        NHPersonalInformation nhPersonal = nhPersonalInformationService.getNHPersonalById(nhId);
        model.addAttribute("newhire", nhPersonal );
        return "newhire_by_id";
    }




    @GetMapping("/delete-newhire")
    public String renderDeleteNH(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "delete_newhire";
    }

    @PostMapping("/delete-newhire")
    public String deleteNH(@RequestParam("nhId") Long nhId){
        nhPersonalInformationService.deleteNH(nhId);
        return "success";
    }

    @GetMapping("/update-phone")
    public String renderUpdatePhone(Model model){
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "update_phone";
    }


    @PostMapping("/update-phone")
    public String updatePhone(@RequestParam("nhId") Long nhId, @RequestParam("phoneNumber") String phoneNumber){
        nhPersonalInformationService.updateNHPhone(nhId,phoneNumber);
        return "success";
    }
}













