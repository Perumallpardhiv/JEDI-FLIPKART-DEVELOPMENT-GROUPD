package com.flipkart.bean;

/**
 * Represents a booking made by a user for a gym slot.
 * It contains details about the booking such as the booking ID, user ID, 
 * slot ID, slot time, and whether the booking is deleted.
 */
public class FlipFitBooking {

    // Unique identifier for the booking
    private int bookingId;
    
    // Unique identifier for the user who made the booking
    public int userId;
    
    // Unique identifier for the gym slot associated with the booking
    private int slotId;
    
    // The time of the gym slot for the booking
    private int slotTime;
    
    // Flag indicating whether the booking has been deleted
    private boolean isdeleted;

    /**
     * Gets the booking ID for this booking.
     *
     * @return the booking ID.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID for this booking.
     *
     * @param bookingId the booking ID to set.
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the user ID for the user who made this booking.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the user who made this booking.
     *
     * @param userId the user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the slot ID for the gym slot associated with this booking.
     *
     * @return the slot ID.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot ID for the gym slot associated with this booking.
     *
     * @param slotId the slot ID to set.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Checks whether this booking has been deleted.
     *
     * @return true if the booking is deleted, false otherwise.
     */
    public boolean isdeleted() {
        return isdeleted;
    }

    /**
     * Sets the deleted status for this booking.
     *
     * @param isdeleted true to mark as deleted, false otherwise.
     */
    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    /**
     * Gets the slot time for this booking.
     *
     * @return the slot time.
     */
    public int getSlotTime() {
        return slotTime;
    }

    /**
     * Sets the slot time for this booking.
     *
     * @param slotTime the slot time to set.
     */
    public void setSlotTime(int slotTime) {
        this.slotTime = slotTime;
    }
}
