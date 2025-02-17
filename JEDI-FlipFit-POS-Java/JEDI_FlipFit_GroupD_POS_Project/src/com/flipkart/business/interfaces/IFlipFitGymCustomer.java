package com.flipkart.business.interfaces;

import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitUser;

import java.util.List;

/**
 * Interface defining the business logic for managing gym customer operations.
 * Provides methods for viewing bookings, handling payments, editing details, and managing customer registration and login.
 */
public interface IFlipFitGymCustomer {

    /**
     * Retrieves a list of all booked slots for a specific user.
     * 
     * @param userId the ID of the user whose bookings need to be retrieved
     * @return a List of FlipFitBooking objects representing the booked slots for the user
     */
    public List<FlipFitBooking> viewBookedSlots(int userId);

    /**
     * Checks if a user has any booking conflicts for a given slot time.
     * 
     * @param userId the ID of the user
     * @param slotTime the time of the slot to check for conflicts
     * @return a FlipFitBooking object if a conflict exists, otherwise null
     */
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime);

    /**
     * Retrieves a list of available gym centres for the customer to view.
     * 
     * @return a List of FlipFitGymCentre objects representing the available gym centres
     */
    public List<FlipFitGymCentre> viewCentres();

    /**
     * Makes a payment for a user's gym membership or booking.
     * 
     * @param userId the ID of the user making the payment
     * @return boolean indicating whether the payment was successfully processed
     */
    public boolean makePayment(int userId);

    /**
     * Allows a user to edit their personal details.
     * 
     * @param flipFitGymCustomer the FlipFitGymCustomer object containing updated user details
     * @return the updated FlipFitGymCustomer object after the details are modified
     * @throws InvalidChoiceException if the user makes an invalid choice during editing
     */
    public FlipFitGymCustomer editDetails(FlipFitGymCustomer flipFitGymCustomer) throws InvalidChoiceException;

    /**
     * Logs in a customer using their credentials.
     * 
     * @param flipFitUser the FlipFitUser object containing login credentials (email and password)
     * @return the FlipFitUser object if login is successful, otherwise null
     */
    public FlipFitUser login(FlipFitUser flipFitUser);

    /**
     * Registers a new customer with the given details.
     * 
     * @param flipFitGymCustomer the FlipFitGymCustomer object containing customer details for registration
     * @return the registered FlipFitGymCustomer object after successful registration
     */
    public FlipFitGymCustomer registerCustomer(FlipFitGymCustomer flipFitGymCustomer);
}
