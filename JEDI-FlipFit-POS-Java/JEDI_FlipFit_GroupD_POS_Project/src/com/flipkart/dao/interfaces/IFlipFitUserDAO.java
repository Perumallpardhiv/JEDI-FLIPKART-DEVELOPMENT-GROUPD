package com.flipkart.dao.interfaces;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;

public interface IFlipFitUserDAO {

    /**
     * Logs in a user as a customer.
     * 
     * @param emailID The email ID of the customer.
     * @param password The password of the customer.
     * @return The user details if login is successful, otherwise null.
     */
    public FlipFitUser loginAsCustomer(String emailID, String password);

    /**
     * Logs in a user as a gym owner.
     * 
     * @param emailID The email ID of the owner.
     * @param password The password of the owner.
     * @return The user details if login is successful, otherwise null.
     */
    public FlipFitUser loginAsOwner(String emailID, String password);

    /**
     * Adds a new user to the system.
     * 
     * @param ffu The user to be added.
     */
    public void addUser(FlipFitUser ffu);

    /**
     * Deletes an existing user.
     * 
     * @param ffu The user to be deleted.
     */
    public void deleteUser(FlipFitUser ffu);

    /**
     * Updates the details of an existing user.
     * 
     * @param ffu The user with updated details.
     */
    public void changeUser(FlipFitUser ffu);

    /**
     * Retrieves a user by their ID.
     * 
     * @param userID The ID of the user to fetch.
     * @return The user details.
     */
    public FlipFitUser getUser(int userID);
}
