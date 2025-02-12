/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class Administrator extends User {
	private String name;
	private String phoneNumber;
	
<<<<<<< HEAD
	public Administrator(String email, String password, int roleId, String name, String phoneNumber) {
		super(email, password, roleId);
=======
	public Administrator(String email, String password, String roleName, String name, String phoneNumber) {
		super(email, password, roleName);
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Administrator() {
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
}
=======
}
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
