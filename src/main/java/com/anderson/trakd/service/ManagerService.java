package com.anderson.trakd.service;

import com.anderson.trakd.model.Manager;
import com.anderson.trakd.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository){
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }


}
