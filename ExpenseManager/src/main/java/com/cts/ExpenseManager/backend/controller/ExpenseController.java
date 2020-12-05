package com.cts.ExpenseManager.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cts.ExpenseManager.backend.dao.CategoryDao;
import com.cts.ExpenseManager.backend.dao.ExpenseDao;
import com.cts.ExpenseManager.backend.dao.UserDao;
import java.util.List;
import java.util.Optional;

import com.cts.ExpenseManager.backend.model. * ;

@Controller
public class ExpenseController {

  @Autowired
  private ExpenseDao expenseDao;

  @Autowired
  private UserDao userDao;

  @Autowired
  private CategoryDao categoryDao;

  @PostMapping("/save-Expense")
  public String submitForm(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("ExpenseForm") Expense expenseForm) {
	  expenseForm.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
	  
	  expenseDao.addExpense(expenseForm);
      return "redirect:/addExpense";
  }

  @RequestMapping(path = "/delete/{expId}")
  public String deleteExpenseById(Model model, @PathVariable("expId") int expId) 
  {
      expenseDao.deleteExpense(expId);
      return "redirect:/addExpense";
  }
  
  @RequestMapping(path = {"/addExpense"}, method = RequestMethod.GET)
  public String addExpense(Model model) {
    List < Expense > expensesList = expenseDao.getAllExpenses();
    model.addAttribute("expensesList", expensesList);
    List<Category> categoriesList =  categoryDao.getAllCategory();
	model.addAttribute("categoriesList", categoriesList);
    return "home/addExpense";
  }
  

  @RequestMapping("/editExpense/{expId}")
  public String showEditExpensePage(@AuthenticationPrincipal UserDetails userDetails,Model model, @PathVariable("expId") Optional<Integer> expId) 
                          
  {
	  List<Category> categoriesList =  categoryDao.getAllCategory();
	  
    	model.addAttribute("categoriesLists", categoriesList);
          Expense exp = expenseDao.getExpense(expId.get());
          exp.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
          model.addAttribute("expense", exp);
          
     
      return "home/edit_Expense";
  }
  
  @RequestMapping(path = "/updateExpense", method = RequestMethod.POST)
  public String createOrUpdateExpnse(@AuthenticationPrincipal UserDetails userDetails,Expense expense) 
  {
	  expense.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
      expenseDao.updateExpense(expense);
      return "redirect:/addExpense";
  }
  
  
}