package com.flipkart.business.interfaces;

import com.flipkart.bean.*;

import java.util.List;

/**
 * Interface representing the business logic for FlipFit Admin operations.
 * It defines methods for managing gym owners, customers, and gym centres.
 */
public interface IFlipFitAdmin {
    
    /**
     * Admin login functionality.
     * 
     * @param flipFitAdmin the admin object containing login credentials
     * @return boolean indicating whether the admin login was successful
     */
    public boolean adminLogin(FlipFitAdmin flipFitAdmin);
    
    /**
     * Retrieves the list of gym owners who are awaiting approval.
     * 
     * @return List of FlipFitGymOwner objects representing the pending owners
     */
    public List<FlipFitGymOwner> getPendingOwnerList();
    
    /**
     * Retrieves the list of gym owners who have been approved.
     * 
     * @return List of FlipFitGymOwner objects representing the approved owners
     */
    public List<FlipFitGymOwner> getApprovedOwnerList();
    
    /**
     * Retrieves a list of gym customers.
     * 
     * @return List of FlipFitGymCustomer objects representing the users
     */
    public List<FlipFitGymCustomer> getUserList();
    
    /**
     * Retrieves a list of gym centres associated with a particular gym owner.
     * 
     * @param ownerId the ID of the gym owner
     * @return List of FlipFitGymCentre objects associated with the given ownerId
     */
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId);
    
    /**
     * Validates a gym owner by their owner ID.
     * 
     * @param ownerId the ID of the gym owner to validate
     * @return boolean indicating whether the gym owner is valid
     */
    public boolean validateOwner(int ownerId);
    
    /**
     * Deletes a gym owner from the system.
     * 
     * @param ownerId the ID of the gym owner to delete
     * @return boolean indicating whether the deletion was successful
     */
    public boolean deleteOwner(int ownerId);
}
