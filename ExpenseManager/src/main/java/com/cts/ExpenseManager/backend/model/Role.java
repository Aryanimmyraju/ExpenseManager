package com.cts.ExpenseManager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
 
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="role_id")
 private int id;
 
 private String name;

public Role() {
	super();
}
public Role(String name) {
    this.name = name;
}

public Role(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


}
