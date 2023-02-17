package com.anderson.trakd.repository;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.model.NHPersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NHPersonalInformationRepository extends JpaRepository<NHPersonalInformation, Long> {

    List<NHPersonalInformation> findByManagerId(Long managerId);

    List<NHPersonalInformation> findByDeptId(Long deptId);

    List<NHPersonalInformation> findByJobTitle(String jobTitle);

}
