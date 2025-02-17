package com.flipkart.business.interfaces;

import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.bean.*;

import java.util.List;

/**
 * Interface defining the business logic for managing gym owner operations.
 * Provides methods for adding and viewing gym centres, managing payments, 
 * editing owner details, and handling owner registration and login.
 */
public interface IFlipFitGymOwner {

    /**
     * Adds a new gym centre under the ownership of the gym owner.
     * 
     * @param flipFitGymCentre the FlipFitGymCentre object containing details of the new gym centre
     * @return the added FlipFitGymCentre object after successfully adding it to the system
     * @throws InvalidChoiceException if the gym centre details are invalid or incomplete
     */
    public FlipFitGymCentre addCentre(FlipFitGymCentre flipFitGymCentre) throws InvalidChoiceException;

    /**
     * Retrieves a list of gym centres owned by the specified gym owner.
     * 
     * @param flipFitGymOwner the FlipFitGymOwner object whose centres are to be viewed
     * @return a List of FlipFitGymCentre objects representing the gym centres owned by the owner
     */
    public List<FlipFitGymCentre> viewCentres(FlipFitGymOwner flipFitGymOwner);

    /**
     * Retrieves a list of all payments related to the gym owner or their centres.
     * 
     * @return a List of FlipFitPayments representing the payments made to the gym owner
     */
    public List<FlipFitPayments> viewPayments();

    /**
     * Edits the details of the gym owner.
     * 
     * @param flipFitGymOwner the FlipFitGymOwner object containing updated details
     * @return the updated FlipFitGymOwner object after modifications
     * @throws InvalidChoiceException if the owner makes an invalid choice or provides invalid details
     */
    public FlipFitGymOwner editDetails(FlipFitGymOwner flipFitGymOwner) throws InvalidChoiceException;

    /**
     * Registers a new gym owner with the given details.
     * 
     * @param owner the FlipFitGymOwner object containing the owner's registration details
     * @return the registered FlipFitGymOwner object after successful registration
     */
    public FlipFitGymOwner registerOwner(FlipFitGymOwner owner);

    /**
     * Logs in a gym owner using their credentials.
     * 
     * @param user the FlipFitUser object containing the gym owner's login credentials (email and password)
     * @return the FlipFitUser object after successful login, or null if login fails
     */
    public FlipFitUser login(FlipFitUser user);

    /**
     * Adds a new slot to a gym centre.
     * 
     * @param flipFitSlot the FlipFitSlots object containing the details of the new slot
     * @return the added FlipFitSlots object after successfully adding it to the system
     */
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlot);
}
