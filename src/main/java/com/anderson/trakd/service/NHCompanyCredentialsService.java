package com.anderson.trakd.service;


import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NHCompanyCredentialsService {

    private final NHCompanyCredentialsRepository nhCompanyCredentialsRepository;

    public NHCompanyCredentialsService(NHCompanyCredentialsRepository nhCompanyCredentialsRepository) {
        this.nhCompanyCredentialsRepository = nhCompanyCredentialsRepository;
    }


    //create  new employee credentials and insert into nhcompany table
    @Transactional
    public void createCompanyCredentials(NHCompanyCredentials nhCompanyCredentials) {
        nhCompanyCredentialsRepository.save(nhCompanyCredentials);
    }


   //get a list of all credentials in nhcompany table
    @Transactional
    public List<NHCompanyCredentials> getAllNHCredentials(){
        return nhCompanyCredentialsRepository.findAll();
    }

}
