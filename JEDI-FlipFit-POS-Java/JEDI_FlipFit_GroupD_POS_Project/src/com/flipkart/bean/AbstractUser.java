package com.flipkart.bean;

/**
 * AbstractUser class represents the common properties of a user.
 * It serves as a base class for users, such as customers or gym owners, 
 * storing details like user ID, name, password, email, phone, and address information.
 */
abstract class AbstractUser {

    // Unique identifier for the user
    private int userId;

    // User's full name
    private String userName;

    // User's password for authentication
    private String password;

    // User's email address
    private String emailID;

    // User's contact phone number
    private String phoneNumber;

    // User's city of residence
    private String city;

    // User's postal code
    private String pinCode;

    // Role of the user: 0 -> customer, 1 -> gym owner
    private int role;

    /**
     * Gets the unique ID of the user.
     * 
     * @return the user's ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID of the user.
     * 
     * @param userId the unique ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the full name of the user.
     * 
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the full name of the user.
     * 
     * @param userName the name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * Gets the email address of the user.
     * 
     * @return the user's email address
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
     * Gets the city where the user resides.
     * 
     * @return the city of the user
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the user resides.
     * 
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the postal code of the user's location.
     * 
     * @return the pin code of the user
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets the postal code of the user's location.
     * 
     * @param pinCode the pin code to set
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Gets the role of the user (e.g., 0 for customer, 1 for gym owner).
     * 
     * @return the user's role ID
     */
    public int getRole() {
        return role;
    }

    /**
     * Sets the role of the user (e.g., 0 for customer, 1 for gym owner).
     * 
     * @param role the role ID to set
     */
    public void setRole(int role) {
        this.role = role;
    }

}
