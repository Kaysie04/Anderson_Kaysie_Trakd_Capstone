package com.anderson.trakd.repository;

import com.anderson.trakd.model.NHCompanyCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NHCompanyCredentialsRepository extends JpaRepository<NHCompanyCredentials, Long> {
}
