package com.anderson.trakd;

import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.NHPersonalInformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NHPersonalInformationRepositoryTest {

    @Autowired
    private NHPersonalInformationRepository repository;

    @Test
    public void testFindByManagerId() {
        Long managerId = 1L;
        List<NHPersonalInformation> personalInformationList = repository.findByManagerId(managerId);
        assertNotNull(personalInformationList, "Result list must not be null");
        assertFalse(personalInformationList.isEmpty(), "Result list must not be empty");

    }

    @Test
    public void testFindByDeptId(){
        Long deptId = 1L;
        List<NHPersonalInformation> personalInformationList = repository.findByDeptId(deptId);
        assertNotNull(personalInformationList, "Result list must not be null");
        assertFalse(personalInformationList.isEmpty(), "Result list must not be empty");

    }

}
