package com.anderson.trakd.controller;
import com.anderson.trakd.model.Dept;
import com.anderson.trakd.model.Manager;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.DeptRepository;
import com.anderson.trakd.repository.ManagerRepository;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import com.anderson.trakd.service.NHPersonalInformationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NHPersonalInformationController {

    @Autowired
    private NHPersonalInformationService nhPersonalInformationService;
    @Autowired
    private NHCompanyCredentialsService nhCompanyCredentialsService;
    @Autowired
    private DeptRepository deptRepository;
    @Autowired
    private ManagerRepository managerRepository;


    // GET ROUTES

    /*Render the main page for creating a newhire
      displays a form for personal information
      displays a list of depts
      displays a list of managers
      displays a list of credentials in order of creation
    */
    @GetMapping("/create-newhire")
    public String createNewHire(Model model){

        model.addAttribute("nhPersonal", new NHPersonalInformation());
        model.addAttribute("dept", deptRepository.findAll());
        model.addAttribute("manager", managerRepository.findAll());
        model.addAttribute("companyCredentials", nhCompanyCredentialsService.getAllNHCredentials());
        return "create_newhire";
    }

    /*
    displays page of all a newhires from nhpersonal table
    will display an option to type in a newhire id number to get their company credentials information
    that hits the /newhire-by-id-company route
     */
    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all_newhires";
    }

    /*
    shows the credentials of one newhire based on the input information from the /all-newhires-personal route
     */
//    @GetMapping("/newhire-by-id-company")
//    public String getNHById(@RequestParam("nhId") Long nhId, Model model) throws Exception {
//        NHPersonalInformation nhPersonal = nhPersonalInformationService.getNHPersonalById(nhId);
//
//        try {
//            if (nhPersonal != null) {
//                model.addAttribute("newhire", nhPersonal);
//                return "newhire_by_id";
//            } else if (!(nhId instanceof Long)) {
//                throw new Exception();
//            }
//        } catch Exception
//    }

    @GetMapping("/newhire-by-id-company")
    public String getNHById(@RequestParam("nhId") Long nhId, Model model) {

        NHPersonalInformation nhPersonal = null;
        try {
            nhPersonal = nhPersonalInformationService.getNHPersonalById(nhId);
        } catch (Exception e) {
            return "error_page";
        }
        return "error_page";
    }


    /*
     Renders a page that displays all personal information from the nhpersonal table
     */
    @GetMapping("/all-newhires")
    public String getAllNewHires(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "all_newhires_general";
    }

    /*
     displays all newhires that are under a specific department
      option to choose from which dept is from /allDepts route
    */
    @GetMapping("/newhires-by-dept")
    public String getAllNHByDeptId(@RequestParam("deptId") Long deptId, Model model){
        List<NHPersonalInformation> nhPersonalList = null;
        try {
            nhPersonalList = nhPersonalInformationService.getNHPersonalByDeptId(deptId);
            Dept deptName = deptRepository.getReferenceById(deptId);
            model.addAttribute("newhires", nhPersonalList );
            model.addAttribute("dept", deptName);
            return "all_newhires_by_dept";
        } catch (Exception e) {
            return "error_page";
        }
    }

    /*
    displays all newhires that are under a specific manager
     option to choose from which manager is from /allManagers route
   */
    @GetMapping("/newhires-by-manager")
    public String getAllNHByManagerId(@RequestParam("managerId") Long managerId, Model model){
        List<NHPersonalInformation> nhPersonalList = null;
        try {
            nhPersonalList = nhPersonalInformationService.getNHPersonalByManagerId(managerId);
            Manager managerName = managerRepository.getReferenceById(managerId);
            model.addAttribute("newhires", nhPersonalList );
            model.addAttribute("manager", managerName);
            return "all_newhires_by_manager";
        } catch (Exception e) {
            return "error_page";
        }
    }

    /*
     displays all job titles and displays an option to select all newhires under a specific job title
      */
    @GetMapping("/allTitles")
    public String renderAllTitles(){
        return "all_titles";
    }

    @GetMapping("/newhires-by-title")
    public String getNHByJob(@RequestParam("jobtitle")String jobTitle, Model model){
        List<NHPersonalInformation> nhPersonalList = null;
        try {
            nhPersonalList = nhPersonalInformationService.getNHByJobTitle(jobTitle);
            model.addAttribute("newhires", nhPersonalList);
            return "all_newhires_by_title";
        } catch (Exception e) {
            return "error_page";
        }
    }

    /*
    renders all newhires from nhpersonal table and displays an option to delete a newhire based on their id
     */
    @GetMapping("/delete-newhire")
    public String renderDeleteNH(Model model) {
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "delete_newhire";
    }

    /*
     renders all newhires from nhpersonal table
     displays an option to update a newhire's phone number based on their id
     */
    @GetMapping("/update-phone")
    public String renderUpdatePhone(Model model){
        model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
        nhPersonalInformationService.getAllNHPersonal();
        return "update_phone";
    }

    // POST ROUTES

    // submits request to save input information to nhpersonal table
    @PostMapping("/create-newhire")
    public String createNewHire(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonalInformation) {
        nhPersonalInformationService.createNHPersonal(nhPersonalInformation);
        return"success";
    }

    // submits request to delete newhire from nhpersonal table
    @PostMapping("/delete-newhire")
    public String deleteNH(@RequestParam("nhId") Long nhId){
        nhPersonalInformationService.deleteNH(nhId);
        return "success";
    }

    //submits request to update phone number in nhpersonal table
//    @PostMapping("/update-phone")
//    public String updatePhone(@RequestParam("nhId") Long nhId, @RequestParam("phoneNumber") String phoneNumber){
//        nhPersonalInformationService.updateNHPhone(nhId,phoneNumber);
//        return "success";
//    }

    @PostMapping("/update-phone")
    public String updatePhone(@RequestParam("nhId") Long nhId, @RequestParam("phoneNumber") String phoneNumber, Model model) {
        try {
            nhPersonalInformationService.updateNHPhone(nhId, phoneNumber);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while updating the phone number");
            return "error_page";
        }
    }
}













