package com.cts.ExpenseManager.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.ExpenseManager.backend.model.*;
public interface CategoryReprository extends JpaRepository<Category, Integer>{

	Category findByCategoryName(String name);
	
}
