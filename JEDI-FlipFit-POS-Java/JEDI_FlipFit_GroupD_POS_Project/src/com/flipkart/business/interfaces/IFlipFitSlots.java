package com.flipkart.business.interfaces;

import com.flipkart.bean.FlipFitSlots;

/**
 * Interface for managing gym slot operations, including availability updates and slot details retrieval.
 */
public interface IFlipFitSlots {

    /**
     * Updates the availability of a gym slot.
     * 
     * @param flipFitSlots the updated slot details.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateAvailability(FlipFitSlots flipFitSlots);

    /**
     * Retrieves the details of a specific gym slot.
     * The implementation defines how the slot details are fetched.
     */
    public void getSlotDetails();
}
