package com.anderson.trakd.service;


import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NHCompanyCredentialsService {

    private final NHCompanyCredentialsRepository nhCompanyCredentialsRepository;

    @Autowired
    public NHCompanyCredentialsService(NHCompanyCredentialsRepository nhCompanyCredentialsRepository) {
        this.nhCompanyCredentialsRepository = nhCompanyCredentialsRepository;
    }

    /*
    create  new employee credentials and insert into nhcompany table
     */
    public void createCompanyCredentials(NHCompanyCredentials nhCompanyCredentials) {
        nhCompanyCredentialsRepository.save(nhCompanyCredentials);
    }

    /*
   get a list of all credentials in nhCompany table
    */
    public List<NHCompanyCredentials> getAllNHCredentials(){
        return nhCompanyCredentialsRepository.findAll();
    }


}
