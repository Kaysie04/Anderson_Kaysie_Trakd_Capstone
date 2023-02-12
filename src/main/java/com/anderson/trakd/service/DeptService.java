package com.anderson.trakd.service;


import com.anderson.trakd.model.Dept;
import com.anderson.trakd.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DeptService {

    private final DeptRepository deptRepository;

    @Autowired
    public DeptService(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    public List<Dept> getAllDepts() {
        return deptRepository.findAll();
    }

}

