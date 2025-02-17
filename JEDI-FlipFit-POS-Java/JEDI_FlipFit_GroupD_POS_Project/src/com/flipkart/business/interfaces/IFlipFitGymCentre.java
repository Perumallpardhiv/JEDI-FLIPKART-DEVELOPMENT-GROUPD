package com.flipkart.business.interfaces;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlots;

import java.util.List;

/**
 * Interface representing the business logic for managing gym centres and slots.
 * It provides methods for updating, deleting gym centres, and viewing available slots.
 */
public interface IFlipFitGymCentre {
    
    /**
     * Updates the details of an existing gym centre.
     * 
     * @param flipFitGymCentre the FlipFitGymCentre object containing updated details of the gym centre
     * @return the updated FlipFitGymCentre object after the update
     */
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre flipFitGymCentre);
    
    /**
     * Deletes a gym centre based on its centre ID.
     * 
     * @param centreId the ID of the gym centre to be deleted
     * @return boolean indicating whether the deletion was successful
     */
    public boolean deleteGymCentre(int centreId);
    
    /**
     * Retrieves a list of available slots for a given gym centre.
     * 
     * @param centreId the ID of the gym centre for which the available slots need to be fetched
     * @return a List of FlipFitSlots objects representing the available slots at the specified gym centre
     */
    public List<FlipFitSlots> viewAvailableSlots(int centreId);
}
