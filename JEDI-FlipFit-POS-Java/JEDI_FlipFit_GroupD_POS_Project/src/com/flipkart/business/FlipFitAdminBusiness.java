package com.flipkart.business;

import com.flipkart.dao.interfaces.IFlipFitAdminDAO;
import com.flipkart.bean.*;
import com.flipkart.business.interfaces.IFlipFitAdmin;
import com.flipkart.dao.FlipFitAdminDAOImpl;

import java.util.List;

/**
 * Business logic for managing FlipFit Admin operations.
 * Implements functionality for admin login, owner validation, user management, and gym centre management.
 */
public class FlipFitAdminBusiness implements IFlipFitAdmin {

    private final IFlipFitAdminDAO flipFitAdminDAOImpl;

    /**
     * Constructor to initialize the Admin DAO.
     * 
     * @param FFAdmin DAO implementation for interacting with the admin-related database operations.
     */
    public FlipFitAdminBusiness(FlipFitAdminDAOImpl FFAdmin) {
        this.flipFitAdminDAOImpl = FFAdmin;
    }

    /**
     * Handles the admin login functionality by verifying admin credentials.
     * 
     * @param flipFitAdmin The admin object containing login credentials (email and password).
     * @return boolean True if login is successful, otherwise false.
     */
    public boolean adminLogin(FlipFitAdmin flipFitAdmin) {
        System.out.println("AdminUserBusiness.adminLogin");
        return flipFitAdminDAOImpl.adminLogin(flipFitAdmin);
    }

    /**
     * Retrieves the list of gym owners who are pending approval.
     * 
     * @return List<FlipFitGymOwner> A list of gym owners awaiting approval.
     */
    public List<FlipFitGymOwner> getPendingOwnerList() {
        System.out.println("AdminUserBusiness.getPendingOwnerList");
        return flipFitAdminDAOImpl.getPendingOwnerList();
    }

    /**
     * Retrieves the list of gym owners who have been approved.
     * 
     * @return List<FlipFitGymOwner> A list of gym owners who are approved.
     */
    public List<FlipFitGymOwner> getApprovedOwnerList() {
        System.out.println("AdminUserBusiness.getApprovedOwnerList");
        return flipFitAdminDAOImpl.getApprovedOwnerList();
    }

    /**
     * Retrieves the list of all gym customers (users).
     * 
     * @return List<FlipFitGymCustomer> A list of all gym customers.
     */
    public List<FlipFitGymCustomer> getUserList() {
        System.out.println("AdminUserBusiness.getUserList");
        return flipFitAdminDAOImpl.getUserList();
    }

    /**
     * Validates the status of a gym owner (e.g., approval or rejection).
     * 
     * @param ownerId The ID of the gym owner to be validated.
     * @return boolean True if the owner is successfully validated, otherwise false.
     */
    public boolean validateOwner(int ownerId) {
        return flipFitAdminDAOImpl.validateOwner(ownerId);
    }

    /**
     * Deletes a gym owner from the system based on their owner ID.
     * 
     * @param ownerId The ID of the gym owner to be deleted.
     * @return boolean True if the owner is successfully deleted, otherwise false.
     */
    public boolean deleteOwner(int ownerId) {
        System.out.println("AdminUserBusiness.deleteOwner" + ownerId);
        flipFitAdminDAOImpl.deleteOwner(ownerId);
        return true;
    }

    /**
     * Retrieves a list of gym centres associated with a particular gym owner.
     * 
     * @param ownerId The ID of the gym owner whose centres are to be fetched.
     * @return List<FlipFitGymCentre> A list of gym centres owned by the specified owner.
     */
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId) {
        System.out.println("AdminUserBusiness.getGymCentreUsingOwnerId " + ownerId);
        return flipFitAdminDAOImpl.getGymCentreUsingOwnerId(ownerId);
    }

    /**
     * Registers a new admin in the system by saving the admin details.
     * 
     * @param Admin The admin object containing the details of the new admin to be registered.
     * @return FlipFitAdmin The registered admin object with its generated ID.
     */
    public FlipFitAdmin registerAdmin(FlipFitAdmin Admin) {
        FlipFitAdmin user = new FlipFitAdmin();
        user.setPassword(Admin.getPassword());
        user.setEmailID(Admin.getEmailID());
        user.setUserID(Admin.getUserID());

        // Register the new admin using the DAO
        return flipFitAdminDAOImpl.addAdmin(Admin);
    }
}
