package com.cts.ExpenseManager.backend.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;



@Entity	
@Table(name="Category")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_Id")
	private int categoryId ;
	
	@Column(name="category_Name")
	private String categoryName;
	
	@Column(name = "category_MaxExp")
	private float categoryMaxExp;
	
	public float getCategoryMaxExp() {
		return categoryMaxExp;
	}

	public void setCategoryMaxExp(float categoryMaxExp) {
		this.categoryMaxExp = categoryMaxExp;
	}
	
	@JsonBackReference
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(targetEntity=Expense.class,mappedBy="expCategory",fetch=FetchType.LAZY)
	private List<Expense> exp = new ArrayList<Expense>();
	
	public Category() {
		super();
	}

	public Category(int categoryId, String categoryName,float categoryMaxExp,User userId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryMaxExp= categoryMaxExp;
		this.userId=userId;
	}
	

	/*
	 * public Category(String categoryName, float categoryMaxExp) { super();
	 * this.categoryName = categoryName; this.categoryMaxExp = categoryMaxExp; }
	 */
	
	

	public int getCategoryId() {
		return categoryId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public List<Expense> getExp() {
		return exp;
	}

	public void setExp(List<Expense> exp) {
		this.exp = exp;
	}
	
}
