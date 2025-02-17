package com.flipkart.business;

import com.flipkart.dao.*;
import com.flipkart.bean.*;
import com.flipkart.business.interfaces.IFlipFitGymCustomer;
import java.util.List;
import com.flipkart.exceptions.InvalidChoiceException;

/**
 * Business logic for managing FlipFit Gym customer operations.
 * Implements functionality for viewing bookings, checking conflicts, editing customer details, and managing payment and registration.
 */
public class FlipFitGymCustomerBusiness implements IFlipFitGymCustomer {

    private final FlipFitGymCustomerDAOImpl flipFitGymCustomerDAOImpl;

    /**
     * Constructor to initialize the customer DAO.
     * 
     * @param FFGymCustomer DAO implementation for interacting with customer-related database operations.
     */
    public FlipFitGymCustomerBusiness(FlipFitGymCustomerDAOImpl FFGymCustomer) {
        this.flipFitGymCustomerDAOImpl = FFGymCustomer;
    }

    /**
     * Retrieves the list of all bookings made by a customer.
     * 
     * @param userId The ID of the customer whose bookings are to be viewed.
     * @return List<FlipFitBooking> A list of bookings for the specified user.
     */
    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        System.out.println("Viewing booked slots:> ");
        
        FlipFitBookingDAOImpl bookingDAO = new FlipFitBookingDAOImpl();
        FlipFitSlotDAOImpl slotDAO = new FlipFitSlotDAOImpl();
        
        // Fetch all bookings for the given user
        List<FlipFitBooking> bookingsList = bookingDAO.getAllBookings(userId);
        
        // Print slot details for each booking
        for (FlipFitBooking booking : bookingsList) {
            FlipFitSlots slotdetails = slotDAO.getSlotDetailsById(booking.getSlotId());
            System.out.println("Booking ID: " + booking.getBookingId() + " Slot timing: " + slotdetails.getSlotTime());
        }
        return bookingsList;
    }

    /**
     * Checks for conflicts in the customer's booking based on the slot time.
     * 
     * @param userId The ID of the customer.
     * @param slotTime The time of the slot being checked for conflicts.
     * @return FlipFitBooking If there is a conflict, returns the conflicting booking; otherwise, returns null.
     */
    @Override
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime) {
        System.out.println("Checking conflict for slot " + slotTime);
        return flipFitGymCustomerDAOImpl.checkBookingConflicts(userId, slotTime);
    }

    /**
     * Retrieves a list of all available gym centres.
     * 
     * @return List<FlipFitGymCentre> A list of all gym centres.
     */
    @Override
    public List<FlipFitGymCentre> viewCentres() {
        System.out.println("view centres called:> ");
        return flipFitGymCustomerDAOImpl.viewCentres();
    }

    /**
     * Initiates the payment process for a user.
     * 
     * @param userId The ID of the customer making the payment.
     * @return boolean True if payment is successfully processed, otherwise false.
     */
    public boolean makePayment(int userId) {
        System.out.println("Make payment called:> ");
        flipFitGymCustomerDAOImpl.makePayment(userId);
        return true;
    }

    /**
     * Edits the details of a gym customer.
     * 
     * @param flipFitGymCustomer The customer object containing updated details.
     * @return FlipFitGymCustomer The updated customer object.
     * @throws InvalidChoiceException If an invalid choice is made during the update process.
     */
    public FlipFitGymCustomer editDetails(FlipFitGymCustomer flipFitGymCustomer) throws InvalidChoiceException {
        // Directly calls the DAO to edit customer details
        return flipFitGymCustomerDAOImpl.editDetails(flipFitGymCustomer);
    }

    /**
     * Registers a new gym customer by creating a user and customer record.
     * 
     * @param flipFitGymCustomer The customer object containing the registration details.
     * @return FlipFitGymCustomer The newly registered customer object.
     */
    @Override
    public FlipFitGymCustomer registerCustomer(FlipFitGymCustomer flipFitGymCustomer) {
        FlipFitUser flipFitUser = new FlipFitUser();
        
        // Setting up the FlipFitUser object from the customer data
        flipFitUser.setPassword(flipFitGymCustomer.getPassword());
        flipFitUser.setEmailID(flipFitGymCustomer.getEmailID());
        flipFitUser.setPhoneNumber(flipFitGymCustomer.getPhoneNumber());
        flipFitUser.setUserName(flipFitGymCustomer.getUserName());
        flipFitUser.setRoleID(1); // Role ID for customer
        flipFitGymCustomer.setRole(1); // Set customer role
        
        // Add the user to the system and register as a customer
        flipFitGymCustomerDAOImpl.addUser(flipFitUser);
        return flipFitGymCustomerDAOImpl.addCustomer(flipFitGymCustomer, flipFitUser);
    }

    /**
     * Logs in a user as a customer by verifying email and password.
     * 
     * @param flipFitUser The user object containing email and password for login.
     * @return FlipFitUser The user object if login is successful, null otherwise.
     */
    @Override
    public FlipFitUser login(FlipFitUser flipFitUser) {
        FlipFitUserDAOImpl userDAO = new FlipFitUserDAOImpl();
        
        flipFitUser.setRoleID(1); // Ensure the user role is set to customer
        
        // Perform login using the user DAO
        flipFitUser = userDAO.loginAsCustomer(flipFitUser.getEmailID(), flipFitUser.getPassword());
        return flipFitUser;
    }
}
