package com.cts.ExpenseManager.backend.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.ExpenseManager.backend.repositories.ExpenseRepository;
import com.cts.ExpenseManager.backend.model.*;
@Transactional
@Service
public class ExpenseDao {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<Expense> getAllExpenses()
	{
		return expenseRepository.findAll();
	}
	  
	public Expense getExpenseByName(String expName){
		return expenseRepository.findByexpName(expName);
	}
	
	public Expense getExpense(int id){
		return expenseRepository.findById(id).get();
	}
	
	public void addExpense(Expense exp){
		expenseRepository.save(exp);
	}
	public void deleteExpense(int id) {
		expenseRepository.deleteById(id);
		
	}
	
	public void updateExpense(Expense exp) {
		expenseRepository.save(exp);
	}
	

}
