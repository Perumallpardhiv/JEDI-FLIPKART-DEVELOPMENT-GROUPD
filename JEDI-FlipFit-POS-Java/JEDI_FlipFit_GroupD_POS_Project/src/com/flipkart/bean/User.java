/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class User {
	private String email;
	private String password;
<<<<<<< HEAD
	private int roleId;
	
	public User(String email, String password, int roleId) {
=======
	private String roleId;
	
	public User(String email, String password, String roleId) {
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}
	
	public User() {
		
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

<<<<<<< HEAD
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
=======
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
