package com.cts.ExpenseManager.backend.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;

@Entity	
@Table(name="Expense")
public class Expense 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exp_id")
	private int expId ;
	
	
	
	@JsonBackReference
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	@Column(name = "exp_name")
	private String expName;
	
	@Column(name="exp_amount")
	private Float expAmount;
	
	@Column(name = "exp_created")
	private Date expCreated;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category expCategory;

	
	public Expense() {
		super();
	}
	
	public Expense(int expId, User userId, String expName, Float expAmount, Date expCreated, Category expCategory) {
		super();
		this.expId = expId;
		this.userId = userId;
		this.expName = expName;
		this.expAmount = expAmount;
		this.expCreated = expCreated;
		this.expCategory = expCategory;
	}

	
	public Expense(int expId, String expName, Float expAmount, Date expCreated)
	{
		super();
		this.expId = expId;
		this.expName = expName;
		this.expAmount =expAmount;
		this.expCreated = expCreated; 
	}
	  
	 
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	@JsonIgnore
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	public Float getExpAmount() {
		return expAmount;
	}
	public void setExpAmount(Float expAmount) {
		this.expAmount = expAmount;
	}
	public Date getExpCreated() {
		return expCreated;
	}
	public void setExpCreated(Date expCreated) {
		this.expCreated = expCreated;
	}
	@JsonIgnore
	public Category getExpCategory() {
		return expCategory;
	}
	public void setExpCategory(Category expCategory) {
		this.expCategory = expCategory;
	}
	
	
	
	
	

}
