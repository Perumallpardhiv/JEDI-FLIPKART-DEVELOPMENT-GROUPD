package com.flipkart.business.interfaces;

import com.flipkart.bean.FlipFitBooking;

/**
 * Interface representing the business logic for managing FlipFit bookings.
 * It defines methods for making and deleting bookings for gym slots.
 */
public interface IFlipFitBookings {
    
    /**
     * Makes a booking for a user for a specific gym centre and slot.
     * 
     * @param userID the ID of the user making the booking
     * @param centreID the ID of the gym centre where the booking is being made
     * @param startTime the start time of the booking
     * @return the FlipFitBooking object representing the created booking, or null if the booking could not be made
     */
    public FlipFitBooking makeBooking(int userID, int centreID, int startTime);
    
    /**
     * Deletes a booking with a given booking ID.
     * 
     * @param bookingId the ID of the booking to be deleted
     * @return boolean indicating whether the deletion was successful
     */
    public boolean deleteBooking(int bookingId);
}
