package com.flipkart.dao.interfaces;

import com.flipkart.bean.*;

import java.util.List;

/**
 * Interface for managing gym customers and their interactions with the gym.
 */
public interface IFlipFitGymCustomerDAO {

    /**
     * Retrieves the list of booked slots for a specific user.
     * 
     * @param userID The ID of the user whose booked slots are to be fetched.
     * @return A list of slots booked by the user.
     */
    public List<FlipFitSlots> viewBookedSlots(int userID);

    /**
     * Checks if there are any conflicts with an existing booking for the given user and slot time.
     * 
     * @param userId The ID of the user.
     * @param slotTime The time of the slot being checked for conflicts.
     * @return A booking object if there is a conflict, otherwise null.
     */
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime);

    /**
     * Retrieves the list of gym centers available for the user.
     * 
     * @return A list of gym centers.
     */
    public List<FlipFitGymCentre> viewCentres();

    /**
     * Makes a payment for the specified user.
     * 
     * @param userID The ID of the user making the payment.
     * @return true if the payment is successful, false otherwise.
     */
    public boolean makePayment(int userID);

    /**
     * Views the payment details for the specified user.
     * 
     * @param userID The ID of the user whose payment details are to be viewed.
     */
    public void viewPaymentDetails(int userID);

    /**
     * Edits the payment details for the specified user.
     * 
     * @param userID The ID of the user whose payment details are to be edited.
     */
    public void editPaymentDetails(int userID);

    /**
     * Adds a new user to the system.
     * 
     * @param user The user to be added.
     * @return The added user object.
     */
    public FlipFitUser addUser(FlipFitUser user);

    /**
     * Edits the details of a gym customer.
     * 
     * @param customer The gym customer whose details are to be edited.
     * @return The updated customer object.
     */
    public FlipFitGymCustomer editDetails(FlipFitGymCustomer customer);

    /**
     * Adds a new gym customer to the system, along with the associated user information.
     * 
     * @param customer The gym customer to be added.
     * @param user The associated user information for the customer.
     * @return The added gym customer object.
     */
    public FlipFitGymCustomer addCustomer(FlipFitGymCustomer customer, FlipFitUser user);
}
