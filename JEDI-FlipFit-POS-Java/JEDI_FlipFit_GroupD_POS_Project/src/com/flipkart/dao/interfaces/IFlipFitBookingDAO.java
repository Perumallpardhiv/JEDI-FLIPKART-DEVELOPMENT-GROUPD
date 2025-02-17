package com.flipkart.dao.interfaces;

import com.flipkart.bean.FlipFitBooking;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for managing bookings in the FlipFit system.
 */
public interface IFlipFitBookingDAO {

    /**
     * Makes a new booking.
     * 
     * @param booking The booking details.
     * @return The created booking.
     */
    public FlipFitBooking makeBooking(FlipFitBooking booking);

    /**
     * Deletes a booking based on its ID.
     * 
     * @param bookingId The ID of the booking to delete.
     * @return true if the booking is successfully deleted, false otherwise.
     */
    boolean deleteBooking(int bookingId);

    /**
     * Retrieves all bookings for a specific user.
     * 
     * @param userId The user's ID whose bookings are to be fetched.
     * @return List of bookings associated with the user.
     */
    public List<FlipFitBooking> getAllBookings(int userId);

    /**
     * Retrieves details of a specific booking based on its ID.
     * 
     * @param bookingId The booking ID for which details are to be fetched.
     * @return List containing booking details.
     */
    public List<FlipFitBooking> getBookingDetails(int bookingId);

    // Placeholder for storing bookings, generally would be used internally or for testing.
    public List<FlipFitBooking> bookings = new ArrayList<>();
}
