package com.flipkart.bean;

/**
 * Represents a gym centre in the FlipFit system.
 * Contains details such as the centre's ID, owner ID, capacity, approval status, 
 * and location details including city, state, and pincode.
 */
public class FlipFitGymCentre {

    // Unique identifier for the gym centre
    private int centreID;

    // Unique identifier for the owner of the gym centre
    private int ownerID;

    // The maximum capacity of the gym centre (number of members it can accommodate)
    private int capacity;

    // Indicates whether the gym centre is approved (true/false)
    private boolean approved;

    // The city where the gym centre is located
    private String city;

    // The state where the gym centre is located
    private String state;

    // The pincode for the gym centre's location
    private String pincode;

    /**
     * Gets the centre ID of the gym.
     *
     * @return the centre ID.
     */
    public int getCentreID() {
        return centreID;
    }

    /**
     * Sets the centre ID for the gym.
     *
     * @param centreID the centre ID to set.
     */
    public void setCentreID(int centreID) {
        this.centreID = centreID;
    }

    /**
     * Gets the owner ID of the gym.
     *
     * @return the owner ID.
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * Sets the owner ID for the gym.
     *
     * @param ownerID the owner ID to set.
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Gets the capacity of the gym.
     *
     * @return the capacity of the gym.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity for the gym.
     *
     * @param capacity the capacity to set.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Checks whether the gym is approved.
     *
     * @return true if the gym is approved, false otherwise.
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets the approval status for the gym.
     *
     * @param approved true if the gym is approved, false otherwise.
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Gets the city where the gym is located.
     *
     * @return the city of the gym.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city for the gym's location.
     *
     * @param city the city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state where the gym is located.
     *
     * @return the state of the gym.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state for the gym's location.
     *
     * @param state the state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the pincode for the gym's location.
     *
     * @return the pincode of the gym.
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets the pincode for the gym's location.
     *
     * @param pincode the pincode to set.
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
