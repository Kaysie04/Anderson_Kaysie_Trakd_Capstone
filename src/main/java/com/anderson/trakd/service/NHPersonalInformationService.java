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


    public NHPersonalInformation getNHPersonalById(Long nhId) {
        boolean exists = nhPersonalInformationRepository.existsById(nhId);
        if(!exists) {
            throw new IllegalStateException("NewHire not found with given id");
        }
        else {
            NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
            return nhPersonal;
        }
    }

    // return a list of newhires under a specific department
    public List<NHPersonalInformation> getNHPersonalByDeptId(Long deptId){
        return nhPersonalInformationRepository.findByDeptId(deptId);
    }

    // return a list of newhires under a specific manager
    public List<NHPersonalInformation> getNHPersonalByManagerId(Long managerId){
        return nhPersonalInformationRepository.findByManagerId(managerId);
    }

    // return a list of newhires under a specific job title
    public List<NHPersonalInformation> getNHByJobTitle(String jobTitle){
        return nhPersonalInformationRepository.findByJobTitle(jobTitle);
    }

    // delete a newhire from the nhpersonal table
    @Transactional
    public void deleteNH(Long nhId){
        nhPersonalInformationRepository.deleteById(nhId);
    }

    // update a newhire phone number
    @Transactional
    public void updateNHPhone(Long nhId, String phoneNumber) {
        NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
        if (nhPersonal!= null) {
            nhPersonal.setPhoneNumber(phoneNumber);
              nhPersonalInformationRepository.save(nhPersonal);
        } else throw new IllegalStateException("not valid id number");
    }

}
