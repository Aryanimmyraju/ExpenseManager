package com.cts.ExpenseManager.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity	
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id" , unique = true)
	private int userId ;
	
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "firstname")
	 private String firstname; 
	 
	 @Column(name = "lastname")
	 private String lastname;
	 
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "active")
	 private int active;
	
	 @ManyToMany(cascade=CascadeType.ALL)
	 @JoinTable(name = "user_role", joinColumns = @JoinColumn (name =  "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	 private Set<Role> roles;
	 
	 @JsonManagedReference
	 @JsonIgnore
	 @OneToMany(targetEntity=Expense.class, cascade = CascadeType.ALL ,mappedBy="userId",fetch=FetchType.LAZY)
	 private List<Category> cat = new ArrayList<>();

 
	 @JsonManagedReference
	 @JsonIgnore
	 @OneToMany(targetEntity=Expense.class, mappedBy="userId",fetch=FetchType.LAZY)
	 private List<Expense> expr = new ArrayList<Expense>();

	public List<Category> getCat() {
		return cat;
	}


	public void setCat(List<Category> cat) {
		this.cat = cat;
	}


	public User(int userId, String email, String firstname, String lastname, String password, int active,
			Set<Role> roles, List<Category> cat, List<Expense> expr) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.cat = cat;
		this.expr = expr;
	}


	public User() {
		super();
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public List<Expense> getExpr() {
		return expr;
	}


	public void setExpr(List<Expense> expr) {
		this.expr = expr;
	}
	

	
}