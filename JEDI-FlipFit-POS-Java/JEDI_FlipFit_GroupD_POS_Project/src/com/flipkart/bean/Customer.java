/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class Customer extends User {
	private String name;
	private String phoneNumber;
	private int age;
	private String address;
	
<<<<<<< HEAD
	public Customer(String email, String password, int roleId, String name, String phoneNumber, int age, String address) {
		super(email, password, roleId);
=======
	public Customer(String email, String password, String roleName, String name, String phoneNumber, int age, String address) {
		super(email, password, roleName);
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.address = address;
	}
	
	public Customer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
<<<<<<< HEAD

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
=======

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
