package com.anderson.trakd.service;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.DeptRepository;
import com.anderson.trakd.repository.ManagerRepository;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import com.anderson.trakd.repository.NHPersonalInformationRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;


@Service
public class NHPersonalInformationService {
    @Autowired
    private NHPersonalInformationRepository nhPersonalInformationRepository;
    @Autowired
    private NHCompanyCredentialsRepository nhCompanyCredentialsRepository;
    @Autowired
    private DeptRepository deptRepository;
    @Autowired
    private ManagerRepository managerRepository;



    //create a new employee and insert into nhPersonal table
    @Transactional
    public void createNHPersonal(NHPersonalInformation nhPersonal){
        nhPersonalInformationRepository.save(nhPersonal);
    }


    //get a list of all employees in nhPersonal table
    public List<NHPersonalInformation> getAllNHPersonal(){
        return nhPersonalInformationRepository.findAll();
    }



    public NHPersonalInformation getNHPersonalById(Long nhId) throws Exception {
        try {
            NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
            return nhPersonal;
        } catch (IllegalArgumentException ex) {
            throw new Exception("Invalid input type. Please enter a valid ID");
        } catch (Exception ex) {
            throw new Exception("Invalid Input");
        }
    }


    // return a list of newhires under a specific department
    public List<NHPersonalInformation> getNHPersonalByDeptId(Long deptId) throws Exception {
        List<NHPersonalInformation> nhPersonalList = nhPersonalInformationRepository.findByDeptId(deptId);
        if(!nhPersonalList.isEmpty()){
            return nhPersonalList;
        } throw new Exception("Invalid Input");

    }

    // return a list of newhires under a specific manager
    public List<NHPersonalInformation> getNHPersonalByManagerId(Long managerId) throws Exception {
        List<NHPersonalInformation> nhPersonalList = nhPersonalInformationRepository.findByManagerId(managerId);
        if(!nhPersonalList.isEmpty()){
            return nhPersonalList;
        } throw new Exception("Invalid Input");

    }

    // return a list of newhires under a specific job title
    public List<NHPersonalInformation> getNHByJobTitle(String jobTitle) throws Exception {
            List<NHPersonalInformation> nhPersonalList = nhPersonalInformationRepository.findByJobTitle(jobTitle);
            if(!nhPersonalList.isEmpty()){
                return nhPersonalList;
            } throw new Exception();

    }

    // delete a newhire from the nhpersonal table
    @Transactional
    public void deleteNH(Long nhId){
        nhPersonalInformationRepository.deleteById(nhId);
    }

    // update a newhire phone number
    @Transactional
    public void updateNHPhone(Long nhId, String phoneNumber) throws Exception {
        NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
        if (nhPersonal!= null) {
            nhPersonal.setPhoneNumber(phoneNumber);
            nhPersonalInformationRepository.save(nhPersonal);
        } else throw new Exception("Invalid Input");
    }

}
