package com.flipkart.dao.interfaces;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlots;

import java.util.ArrayList;

/**
 * Interface for managing gym centers in the FlipFit system.
 */
public interface IFlipFitGymCentreDAO {

    /**
     * Creates a new gym center.
     * 
     * @param FFGC The gym center details.
     * @return The created gym center.
     */
    public FlipFitGymCentre createGymCentre(FlipFitGymCentre FFGC);

    /**
     * Updates the details of an existing gym center.
     * 
     * @param FFGC The updated gym center details.
     * @return The updated gym center.
     */
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC);

    /**
     * Deletes a gym center.
     * 
     * @param FFGC The gym center to delete.
     */
    public void deleteGymCentre(FlipFitGymCentre FFGC);

    /**
     * Retrieves available slots for a specific gym center.
     * 
     * @param FFGC The gym center for which available slots are to be fetched.
     * @return A list of available slots for the gym center.
     */
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC);

    /**
     * Retrieves a list of gym centers in a specific city.
     * 
     * @param city The city where gym centers need to be viewed.
     * @return A list of gym centers in the given city.
     */
    public ArrayList<FlipFitGymCentre> viewCentres(String city);
}
