package com.cts.ExpenseManager.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.ExpenseManager.backend.model.*;



public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	

	Expense findByexpName(String expName);


	@Query("select e from Expense e where e.userId.userId=:id")
	List<Expense> findExpenseByUser(@Param("id") int id);
	
}
