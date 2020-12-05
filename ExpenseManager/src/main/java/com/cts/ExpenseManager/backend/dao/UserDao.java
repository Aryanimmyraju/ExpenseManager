package com.cts.ExpenseManager.backend.dao;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.ExpenseManager.backend.repositories.*;
import com.cts.ExpenseManager.backend.model.*;
@Transactional
@Service("userService")
public class UserDao {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	 public List<User> getAllUser()
		{
			return userRepository.findAll();
		}
		  
		
		
		public User getUser(int id){
			return userRepository.findById(id).get();
		}
		
		
		
		
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 
	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  user.setActive(1);
	  Role userRole = roleRespository.findByRole("ADMIN");
	  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);
	 }

}
	


