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

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Service
public class NHPersonalInformationService {

    private final NHPersonalInformationRepository nhPersonalInformationRepository;
    private final NHCompanyCredentialsRepository nhCompanyCredentialsRepository;
    private final DeptRepository deptRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public NHPersonalInformationService(NHPersonalInformationRepository nhPersonalInformationRepository, NHCompanyCredentialsRepository nhCompanyCredentialsRepository, DeptRepository deptRepository, ManagerRepository managerRepository){
        this.nhPersonalInformationRepository = nhPersonalInformationRepository;
        this.nhCompanyCredentialsRepository = nhCompanyCredentialsRepository;
        this.deptRepository = deptRepository;
        this.managerRepository = managerRepository;
    }

    /*
    create a new employee and insert into nhPersonal table
     */
    public void createNHPersonal(NHPersonalInformation nhPersonal){
        nhPersonalInformationRepository.save(nhPersonal);
    }

    /*
    get a list of all employees in nhPersonal table
     */
    public List<NHPersonalInformation> getAllNHPersonal(){
        return nhPersonalInformationRepository.findAll();
    }

//    public NHPersonalInformation addCompanyCredentials(Long nhId, String credentialsId) {
//
//        NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
//        NHCompanyCredentials nhCredentials = nhCompanyCredentialsRepository.getReferenceById(credentialsId);
//
//        if(nhPersonal != null && nhCredentials !=null){
//            nhPersonal.setCompanyCredentials(nhCredentials);
//
//            return nhPersonalInformationRepository.save(nhPersonal);
//
//        } else throw new IllegalArgumentException("Cannot find information based on ID's given");
//    }


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

    public void addCompanyCredentials(Long nhId, String companyId){
        NHPersonalInformation nhPersonal = nhPersonalInformationRepository.getReferenceById(nhId);
        NHCompanyCredentials nhCompanyCredentials = nhCompanyCredentialsRepository.getReferenceById(companyId);
        nhPersonal.setCompanyCredentials(nhCompanyCredentials);
        nhPersonalInformationRepository.save(nhPersonal);
    }

}
