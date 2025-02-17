package com.flipkart.bean;

public class FlipFitUser {

    // Unique ID for the user
    private int userID;

    // Role ID to specify the user's role (e.g., Customer, Gym Owner, etc.)
    private int roleID;

    // User's email address
    private String emailID;

    // User's password for authentication
    private String password;

    // User's name
    private String userName;

    // User's phone number
    private String phoneNumber;

    /**
     * Gets the email address of the user.
     * 
     * @return the email address of the user
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param emailID the email address to set
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role ID of the user (e.g., customer, gym owner).
     * 
     * @return the role ID of the user
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * Sets the role ID for the user.
     * 
     * @param roleID the role ID to set
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * Gets the unique user ID.
     * 
     * @return the user's unique ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the unique user ID.
     * 
     * @param userID the unique user ID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the phone number of the user.
     * 
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user name of the user.
     * 
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name of the user.
     * 
     * @param userName the user name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
