package com.anderson.trakd.service;

import com.anderson.trakd.model.Admin;
import com.anderson.trakd.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
