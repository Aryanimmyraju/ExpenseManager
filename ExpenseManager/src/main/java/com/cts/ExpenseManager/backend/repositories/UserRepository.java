package com.cts.ExpenseManager.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.ExpenseManager.backend.model.*;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByEmail(String email);
   
}