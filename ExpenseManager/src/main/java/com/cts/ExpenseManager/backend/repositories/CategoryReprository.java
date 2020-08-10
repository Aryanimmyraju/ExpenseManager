package com.cts.ExpenseManager.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.ExpenseManager.backend.model.*;
public interface CategoryReprository extends JpaRepository<Category, Integer>{

	Category findByCategoryName(String name);
	
	 
	
	@Query("select c from Category c where c.userId.userId=:id")
	List<Category> findCategoryByUser(@Param("id") int id);
	
}
