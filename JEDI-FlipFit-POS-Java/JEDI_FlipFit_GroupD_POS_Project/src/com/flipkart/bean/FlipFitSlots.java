package com.flipkart.bean;

public class FlipFitSlots {

    // Unique ID for the slot
    private int slotId;

    // ID of the gym center where the slot is available
    private int centreId;

    // Time of the slot (e.g., 9:00 AM or 15:00 PM)
    private int slotTime;

    // Number of available seats in the slot
    private int seatsAvailable;

    /**
     * Constructor to initialize a new FlipFitSlot with the given centre ID, slot time, and available seats.
     * 
     * @param centreId the gym center ID where the slot is located
     * @param slotTime the time of the slot
     * @param seatsAvailable the number of seats available in the slot
     */
    public FlipFitSlots(int centreId, int slotTime, int seatsAvailable) {
        this.centreId = centreId;
        this.slotTime = slotTime;
        this.seatsAvailable = seatsAvailable;
    }

    // Default constructor
    public FlipFitSlots() { }

    /**
     * Gets the slot ID.
     * 
     * @return the slot ID
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot ID.
     * 
     * @param slotId the ID of the slot
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Gets the center ID associated with the slot.
     * 
     * @return the center ID
     */
    public int getCentreId() {
        return centreId;
    }

    /**
     * Sets the center ID associated with the slot.
     * 
     * @param centreId the gym center ID
     */
    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }

    /**
     * Gets the slot time.
     * 
     * @return the slot time (e.g., 9:00 AM)
     */
    public int getSlotTime() {
        return slotTime;
    }

    /**
     * Sets the slot time.
     * 
     * @param slotTime the time for the slot
     */
    public void setSlotTime(int slotTime) {
        this.slotTime = slotTime;
    }

    /**
     * Gets the number of available seats in the slot.
     * 
     * @return the number of available seats
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    /**
     * Sets the number of available seats in the slot.
     * 
     * @param seatsAvailable the number of available seats
     */
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
