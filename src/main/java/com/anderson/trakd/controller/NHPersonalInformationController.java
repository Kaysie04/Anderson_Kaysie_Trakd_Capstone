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
    public String createNewHire(HttpSession session, Model model) {
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("userEmail", email);
            model.addAttribute("nhPersonal", new NHPersonalInformation());
            model.addAttribute("dept", deptRepository.findAll());
            model.addAttribute("manager", managerRepository.findAll());
            model.addAttribute("companyCredentials", nhCompanyCredentialsService.getAllNHCredentials());
            return "create_newhire";
        } else {
            return "redirect:/login";
        }
    }

    /*
    displays page of all a newhires from nhpersonal table
    will display an option to type in a newhire id number to get their company credentials information
    that hits the /newhire-by-id-company route
     */
    @GetMapping("/all-newhires-personal")
    public String getAllNewHiresPersonal(HttpSession session, Model model) {
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
            nhPersonalInformationService.getAllNHPersonal();
            return "all_newhires";
        } else {
            return "redirect:/login";
        }
    }

    /*
    shows the credentials of one newhire based on the input information from the /all-newhires-personal route
     */
    @GetMapping("/newhire-by-id-company")
    public String getNHById(@RequestParam("nhId") Long nhId, HttpSession session, Model model) {
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            NHPersonalInformation nhPersonal = null;
            try {
                nhPersonal = nhPersonalInformationService.getNHPersonalById(nhId);
                if (nhPersonal != null) {
                    model.addAttribute("newhire", nhPersonal);
                    return "newhire_by_id";
                }
            } catch (Exception e) {
                return "error";
            }
        } else {
            return "redirect:/login";
        }

        return "error";
    }


    /*
     Renders a page that displays all personal information from the nhpersonal table
     */
    @GetMapping("/all-newhires")
    public String getAllNewHires( HttpSession session, Model model) {
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
            nhPersonalInformationService.getAllNHPersonal();
            return "all_newhires_general";
        } else {
            return "redirect:/login";
        }
    }

    /*
     displays all newhires that are under a specific department
      option to choose from which dept is from /allDepts route
    */
    @GetMapping("/newhires-by-dept")
    public String getAllNHByDeptId(@RequestParam("deptId") Long deptId, HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            List<NHPersonalInformation> nhPersonalList = null;
            try {
                nhPersonalList = nhPersonalInformationService.getNHPersonalByDeptId(deptId);
                Dept deptName = deptRepository.getReferenceById(deptId);
                model.addAttribute("newhires", nhPersonalList);
                model.addAttribute("dept", deptName);
                return "all_newhires_by_dept";
            } catch (Exception e) {
                return "error";
            }
        } else {
            return "redirect:/login";
        }
    }

    /*
    displays all newhires that are under a specific manager
     option to choose from which manager is from /allManagers route
   */
    @GetMapping("/newhires-by-manager")
    public String getAllNHByManagerId(@RequestParam("managerId") Long managerId, HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            List<NHPersonalInformation> nhPersonalList = null;
            try {
                nhPersonalList = nhPersonalInformationService.getNHPersonalByManagerId(managerId);
                Manager managerName = managerRepository.getReferenceById(managerId);
                model.addAttribute("newhires", nhPersonalList);
                model.addAttribute("manager", managerName);
                return "all_newhires_by_manager";
            } catch (Exception e) {
                return "error";
            }
        }else {
            return "redirect:/login";
        }
    }

    /*
     displays all job titles and displays an option to select all newhires under a specific job title
      */
    @GetMapping("/allTitles")
    public String renderAllTitles(HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            return "all_titles";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/newhires-by-title")
    public String getNHByJob(@RequestParam("jobtitle")String jobTitle, HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            List<NHPersonalInformation> nhPersonalList = null;
            try {
                nhPersonalList = nhPersonalInformationService.getNHByJobTitle(jobTitle);
                model.addAttribute("newhires", nhPersonalList);
                return "all_newhires_by_title";
            } catch (Exception e) {
                return "error";
            }
        }else {
            return "redirect:/login";
        }
    }

    /*
     renders all newhires from nhpersonal table
     displays an option to update a newhire's phone number based on their id
     */
    @GetMapping("/update-phone")
    public String renderUpdatePhone(HttpSession session, Model model){
        // ensure user is logged in
        String email = (String) session.getAttribute("user");
        if (email != null) {
            model.addAttribute("allNewhires", nhPersonalInformationService.getAllNHPersonal());
            nhPersonalInformationService.getAllNHPersonal();
            return "update_phone";
        }else {
            return "redirect:/login";
        }
    }

    // POST ROUTES

    // submits request to save input information to nhpersonal table
    @PostMapping("/create-newhire")
    public String createNewHire(@ModelAttribute("nhPersonal") NHPersonalInformation nhPersonalInformation) {
        nhPersonalInformationService.createNHPersonal(nhPersonalInformation);
        return"success";
    }

    // submits request to update newhire phone number
    @PostMapping("/update-phone")
    public String updatePhone(@RequestParam("nhId") Long nhId, @RequestParam("phoneNumber") String phoneNumber, Model model) {
        try {
            nhPersonalInformationService.updateNHPhone(nhId, phoneNumber);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while updating the phone number");
            return "error";
        }
    }
}













