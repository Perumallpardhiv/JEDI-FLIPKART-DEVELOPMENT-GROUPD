package com.flipkart.dao.interfaces;

import com.flipkart.bean.*;

import java.util.List;

/**
 * Interface for managing admin operations in the FlipFit system.
 */
public interface IFlipFitAdminDAO {

    /**
     * Validates admin login credentials.
     * 
     * @param flipFitAdmin The admin's credentials.
     * @return true if login is successful, false otherwise.
     */
    public boolean adminLogin(FlipFitAdmin flipFitAdmin);

    /**
     * Retrieves the list of gym owners with pending approval.
     * 
     * @return List of pending gym owners.
     */
    public List<FlipFitGymOwner> getPendingOwnerList();

    /**
     * Retrieves the list of approved gym owners.
     * 
     * @return List of approved gym owners.
     */
    public List<FlipFitGymOwner> getApprovedOwnerList();

    /**
     * Retrieves the list of gym customers.
     * 
     * @return List of gym customers.
     */
    public List<FlipFitGymCustomer> getUserList();

    /**
     * Retrieves a list of gym centres for a specific gym owner.
     * 
     * @param ownerId The owner's ID.
     * @return List of gym centres associated with the owner.
     */
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId);

    /**
     * Validates a gym owner.
     * 
     * @param ownerId The owner's ID to validate.
     * @return true if the owner is valid, false otherwise.
     */
    public boolean validateOwner(int ownerId);

    /**
     * Deletes a gym owner.
     * 
     * @param ownerId The owner's ID to delete.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteOwner(int ownerId);
    
    /**
     * Adds a new admin to the system.
     * 
     * @param Admin The admin details to add.
     * @return The added admin.
     */
    public FlipFitAdmin addAdmin(FlipFitAdmin Admin);
}
