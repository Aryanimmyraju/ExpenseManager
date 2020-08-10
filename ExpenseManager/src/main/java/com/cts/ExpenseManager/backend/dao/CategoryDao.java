package com.cts.ExpenseManager.backend.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cts.ExpenseManager.backend.repositories.CategoryReprository;
import com.cts.ExpenseManager.backend.model.*;




@Transactional
@Service
public class CategoryDao {
	
	@Autowired
	private CategoryReprository categoryReprository;
	
	public List<Category> getAllCategory()
	{
		return categoryReprository.findAll();
	}
	  
	public Category getCategoryByName(String name){
		return categoryReprository.findByCategoryName(name);
	}
	
	public List<Category> getCategoryByUser(int id) {
		return categoryReprository.findCategoryByUser(id);
	}
	public Category getCategory(int id){
		return categoryReprository.findById(id).get();
	}
	
	public void addCategory(Category cat){
		categoryReprository.save(cat);
	}
	public void deleteCat(int id) {
		categoryReprository.deleteById(id);
		
	}
	
	
	public void updateCat(Category cat) {
		categoryReprository.save(cat);
	}
	

}
