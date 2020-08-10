package com.cts.ExpenseManager.backend.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cts.ExpenseManager.backend.model.*;
import com.cts.ExpenseManager.backend.dto.*;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
