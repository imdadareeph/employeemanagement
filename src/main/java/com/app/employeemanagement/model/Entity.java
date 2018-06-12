package com.app.employeemanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity(name="Entity")
public class Entity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="mobile")
	private String mobile;
	@Column(name="address")
	private String address;
	@Column(name="nationality")
	private String nationality;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Entity(){}
	

	public Entity(int id, String name, String mobile, String address, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.nationality = nationality;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
}


