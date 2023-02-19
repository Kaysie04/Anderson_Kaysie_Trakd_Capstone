package com.anderson.trakd.repository;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.model.NHPersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NHPersonalInformationRepository extends JpaRepository<NHPersonalInformation, Long> {

    @Override
    Optional<NHPersonalInformation> findById(Long aLong);

    List<NHPersonalInformation> findByManagerId(Long managerId);

    List<NHPersonalInformation> findByDeptId(Long deptId);

    List<NHPersonalInformation> findByJobTitle(String jobTitle);

}
