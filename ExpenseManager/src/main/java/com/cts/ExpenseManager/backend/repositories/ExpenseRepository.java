package com.cts.ExpenseManager.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.ExpenseManager.backend.model.*;



public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	

	Expense findByexpName(String expName);
	
}
