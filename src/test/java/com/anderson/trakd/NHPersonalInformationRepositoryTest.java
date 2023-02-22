package com.anderson.trakd;

import com.anderson.trakd.model.NHPersonalInformation;
import com.anderson.trakd.repository.NHPersonalInformationRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NHPersonalInformationRepositoryTest {

    @Autowired
    private NHPersonalInformationRepository repository;

    @ParameterizedTest
    @CsvSource({"3"})
    public void testFindByManagerId(Long managerId) {
        List<NHPersonalInformation> personalInformationList = repository.findByManagerId(managerId);
        assertNotNull(personalInformationList, "Result list must not be null");
        assertFalse(personalInformationList.isEmpty(), "Result list must not be empty");
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void testFindByDeptId(Long deptId){
        List<NHPersonalInformation> personalInformationList = repository.findByDeptId(deptId);
        assertNotNull(personalInformationList, "Result list must not be null");
        assertFalse(personalInformationList.isEmpty(), "Result list must not be empty");
    }

    @ParameterizedTest
    @CsvSource({"Entry Software Engineer"})
    public void testFindByJobTitle(String jobTitle){
        List<NHPersonalInformation> personalInformationList = repository.findByJobTitle(jobTitle);
        assertNotNull(personalInformationList, "Result list must not be null");
        assertFalse(personalInformationList.isEmpty(), "Result list must not be empty");
    }

}
