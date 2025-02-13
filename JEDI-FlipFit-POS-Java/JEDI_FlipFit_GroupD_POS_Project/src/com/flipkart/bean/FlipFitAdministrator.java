/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class FlipFitAdministrator extends FlipFitUser {
	private String name;
	private String phoneNumber;
	
	public FlipFitAdministrator(String email, String password, int roleId, String name, String phoneNumber) {
		super(email, password, roleId);
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public FlipFitAdministrator() {
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
}