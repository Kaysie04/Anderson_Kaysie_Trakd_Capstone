package com.anderson.trakd.repository;

import java.util.Optional;

import com.anderson.trakd.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {

    Optional<Dept> findById(int deptId);
}
