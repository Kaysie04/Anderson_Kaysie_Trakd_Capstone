package com.anderson.trakd.controller;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NHCompanyCredentialsRestController {

    private final NHCompanyCredentialsService nhCompanyCredentialsService;



    public NHCompanyCredentialsRestController(NHCompanyCredentialsService nhCompanyCredentialsService) {
        this.nhCompanyCredentialsService = nhCompanyCredentialsService;
    }

    //Create newhire first form information. Retrieves JSON body and saves to database
    @PostMapping("/generate-credentials")
    public void createCredentials(@RequestBody NHCompanyCredentials nhCompanyCredentials) {
        nhCompanyCredentialsService.createCompanyCredentials(nhCompanyCredentials);
    }
}
