package com.anderson.trakd.controller;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import com.anderson.trakd.repository.NHPersonalInformationRepository;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

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
