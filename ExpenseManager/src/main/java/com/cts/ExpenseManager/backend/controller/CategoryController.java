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
import com.cts.ExpenseManager.backend.dao.UserDao;
import com.cts.ExpenseManager.backend.model.*;
import java.util.List;
import java.util.Optional;


@Controller
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(path = {"/addCategory"}, method = RequestMethod.GET)
	  public String addCategory(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		List<Category> categoriesList = categoryDao.getCategoryByUser(userDao.findUserByEmail((userDetails.getUsername())).getUserId());
		model.addAttribute("categoriesList", categoriesList);
	    return "home/addCategory";
	  }
	
	 @PostMapping("/save-category")
	  public String submitFormCat(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("categoryForm") Category categoryForm ) {
		 categoryForm.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
		  
		categoryDao.addCategory(categoryForm);
	      return "redirect:/addCategory";
	  }
		
	 @RequestMapping(path = "/deletecat/{categoryId}")
	  public String deleteCategoryById(Model model, @PathVariable("categoryId") int categoryId) 
	  {
	      categoryDao.deleteCat(categoryId);
	      return "redirect:/addCategory";
	  }
	 
	 @RequestMapping("/editCategory/{categoryId}")
	  public String showEditCategoryPage(@AuthenticationPrincipal UserDetails userDetails,Model model, @PathVariable("categoryId") Optional<Integer> categoryId) 
	                          
	  {
		  
	          Category cat = categoryDao.getCategory(categoryId.get());
	          cat.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
	          model.addAttribute("category", cat);
	          
	     
	      return "home/edit_Category";
	  }
	  
	  @RequestMapping(path = "/updateCategory", method = RequestMethod.POST)
	  public String createOrUpdateCategory(@AuthenticationPrincipal UserDetails userDetails,Category category) 
	  {
		  category.setUserId(userDao.findUserByEmail((userDetails.getUsername())));
	      categoryDao.updateCat(category);
	      return "redirect:/addCategory";
	  }

}
