package com.flipkart.bean;

/**
 * Represents an admin user in the system with essential credentials 
 * including user ID, password, and email ID.
 */
public class FlipFitAdmin {

    // Unique identifier for the admin user
    private int userId;
    
    // Password for the admin's account
    private String password;
    
    // Email ID associated with the admin account
    private String emailID;

    /**
     * Sets the user ID for the admin.
     *
     * @param userId the user ID to set.
     */
    public void setUserID(int userId){
        this.userId = userId;
    }

    /**
     * Sets the password for the admin.
     *
     * @param password the password to set.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Sets the email ID for the admin.
     *
     * @param emailID the email ID to set.
     */
    public void setEmailID(String emailID){
        this.emailID = emailID;
    }

    /**
     * Gets the user ID of the admin.
     *
     * @return the user ID.
     */
    public int getUserID(){
        return this.userId;
    }

    /**
     * Gets the password of the admin.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email ID of the admin.
     *
     * @return the email ID.
     */
    public String getEmailID() {
        return emailID;
    }
}
