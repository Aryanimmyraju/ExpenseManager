package com.cts.ExpenseManager.backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	
	
	 	private String firstName;
	    private String lastName;
	    private String email;
	    private String password;

	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(
	                    name = "user_id", referencedColumnName = "user_id"),
	            inverseJoinColumns = @JoinColumn(
	                    name = "role_id", referencedColumnName = "role_id"))
	    private Collection<Role> roles;
	
		 
	 @JsonManagedReference
	 @JsonIgnore
	 @OneToMany(targetEntity=Category.class, cascade = CascadeType.ALL ,mappedBy="userId",fetch=FetchType.LAZY)
	 private List<Category> cat = new ArrayList<>();

 
	 @JsonManagedReference
	 @JsonIgnore
	 @OneToMany(targetEntity=Expense.class, mappedBy="userId",fetch=FetchType.LAZY)
	 private List<Expense> expr = new ArrayList<Expense>();


	public User() {
		super();
	}


	public User(int userId, String firstName, String lastName, String email, String password,
			Collection<Role> roles, List<Category> cat, List<Expense> expr) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.cat = cat;
		this.expr = expr;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public List<Category> getCat() {
		return cat;
	}


	public void setCat(List<Category> cat) {
		this.cat = cat;
	}


	public List<Expense> getExpr() {
		return expr;
	}


	public void setExpr(List<Expense> expr) {
		this.expr = expr;
	}

	}