package com.anderson.trakd.service;



import com.anderson.trakd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService extends JpaRepository<User, Long> {
	
	User findUserByEmail(String email);
}
