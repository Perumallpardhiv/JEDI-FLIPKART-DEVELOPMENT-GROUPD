package com.flipkart.business;

import com.flipkart.dao.*;
import com.flipkart.dao.FlipFitGymOwnerDAOImpl;
import com.flipkart.dao.interfaces.IFlipFitGymOwnerDAO;
import com.flipkart.bean.*;
import com.flipkart.business.interfaces.IFlipFitGymOwner;
import com.flipkart.exceptions.InvalidChoiceException;
import java.util.List;

/**
 * Business logic for managing FlipFit gym owners.
 * Implements operations for adding gym centres, slots, viewing centres, payments, editing owner details, and registration.
 */
public class FlipFitGymOwnerBusiness implements IFlipFitGymOwner {

    private final IFlipFitGymOwnerDAO flipFitGymOwnerDAO;

    /**
     * Constructor to initialize the gym owner DAO.
     * 
     * @param FFOwner DAO implementation for interacting with gym owner-related database operations.
     */
    public FlipFitGymOwnerBusiness(FlipFitGymOwnerDAOImpl FFOwner){
        this.flipFitGymOwnerDAO = FFOwner;
    }

    /**
     * Adds a new gym centre to the system.
     * 
     * @param flipFitGymCentre The gym centre object containing information about the new centre.
     * @return FlipFitGymCentre The added gym centre object.
     */
    public FlipFitGymCentre addCentre(FlipFitGymCentre flipFitGymCentre) {
        return flipFitGymOwnerDAO.addCentre(flipFitGymCentre);
    }

    /**
     * Adds a new gym slot to the system.
     * 
     * @param flipFitSlot The gym slot object containing information about the new slot.
     * @return FlipFitSlots The added slot object.
     */
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlot) {
        FlipFitSlotDAOImpl flipFitSlotDAOImpl = new FlipFitSlotDAOImpl();
        flipFitSlotDAOImpl.addSlot(flipFitSlot);
        return flipFitSlot;
    }
    
    /**
     * Retrieves the list of slots for a specific gym centre.
     * 
     * @param centreID The ID of the gym centre whose slots are to be fetched.
     * @return List<FlipFitSlots> A list of slots for the specified gym centre.
     */
    public List<FlipFitSlots> viewSlots(int centreID) {
        // Use the FlipFitSlotDAOImpl to fetch the slots for the given centre ID
        FlipFitSlotDAOImpl flipFitSlotDAOImpl = new FlipFitSlotDAOImpl();
        List<FlipFitSlots> slotList = flipFitSlotDAOImpl.getAllSlots(centreID);
        return slotList;
    }

    

    /**
     * Retrieves the list of gym centres associated with a specific gym owner.
     * 
     * @param flipFitGymOwner The gym owner object used to fetch the centres associated with the owner.
     * @return List<FlipFitGymCentre> A list of gym centres owned by the specified owner.
     */
    public List<FlipFitGymCentre> viewCentres(FlipFitGymOwner flipFitGymOwner) {
        System.out.println("Centres listed:> ");
        return flipFitGymOwnerDAO.viewCentresByOwnerID(flipFitGymOwner);
    }

    /**
     * Retrieves the list of payments made by customers.
     * 
     * @return List<FlipFitPayments> A list of payments (currently a placeholder, implementation can be added).
     */
    public List<FlipFitPayments> viewPayments() {
        System.out.println("Payments listed:> ");
        return null; // Placeholder for future functionality
    }

    /**
     * Edits the details of an existing gym owner.
     * 
     * @param owner The gym owner object with updated details.
     * @return FlipFitGymOwner The updated gym owner object.
     * @throws InvalidChoiceException If an invalid choice is made during the update process.
     */
    public FlipFitGymOwner editDetails(FlipFitGymOwner owner) throws InvalidChoiceException {
        return flipFitGymOwnerDAO.editDetails(owner);
    }

    /**
     * Registers a new gym owner in the system by creating a user and owner record.
     * 
     * @param GymOwner The gym owner object containing the registration details.
     * @return FlipFitGymOwner The newly registered gym owner object.
     */
    public FlipFitGymOwner registerOwner(FlipFitGymOwner GymOwner) {
        FlipFitUser user = new FlipFitUser();
        
        // Setting up the FlipFitUser object with owner's details
        user.setPassword(GymOwner.getPassword());
        user.setEmailID(GymOwner.getEmailID());
        user.setPhoneNumber(GymOwner.getPhoneNumber());
        user.setUserName(GymOwner.getUserName());
        user.setRoleID(2); // Role ID for gym owner
        GymOwner.setRole(2); // Set the role of the gym owner

        // Add the user and gym owner to the system
        user = flipFitGymOwnerDAO.addUser(user);
        return flipFitGymOwnerDAO.addGymOwner(GymOwner, user);
    }

    /**
     * Logs in a user as a gym owner by verifying email and password.
     * 
     * @param flipFitUser The user object containing the login credentials (email and password).
     * @return FlipFitUser The user object if login is successful, null otherwise.
     */
    @Override
    public FlipFitUser login(FlipFitUser flipFitUser) {
        FlipFitUserDAOImpl userDAO = new FlipFitUserDAOImpl();
        flipFitUser.setRoleID(2); // Ensure the user role is set to gym owner
        
        // Perform login using the user DAO
        flipFitUser = userDAO.loginAsOwner(flipFitUser.getEmailID(), flipFitUser.getPassword());
        return flipFitUser;
    }
}
