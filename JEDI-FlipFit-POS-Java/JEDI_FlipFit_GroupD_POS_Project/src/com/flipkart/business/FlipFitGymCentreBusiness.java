package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.business.interfaces.IFlipFitGymCentre;
import com.flipkart.dao.FlipFitGymCentreDAOImpl;

import java.util.List;

/**
 * Business logic for managing FlipFit Gym Centres.
 * Implements functionalities like updating, deleting, and viewing available slots for gym centres.
 */
public class FlipFitGymCentreBusiness implements IFlipFitGymCentre {

    private final FlipFitGymCentreDAOImpl gymCentreDAO;

    /**
     * Constructor to initialize the Gym Centre DAO.
     * 
     * @param FFCentre DAO implementation for interacting with the gym centre-related database operations.
     */
    public FlipFitGymCentreBusiness(FlipFitGymCentreDAOImpl FFCentre) {
        this.gymCentreDAO = FFCentre;
    }

    /**
     * Updates the details of a gym centre.
     * 
     * @param flipFitGymCentre The gym centre object containing updated details.
     * @return FlipFitGymCentre The updated gym centre object.
     */
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre flipFitGymCentre) {
        System.out.println("Updating Gym Centre:> ");
        gymCentreDAO.updateGymCentre(flipFitGymCentre);
        return flipFitGymCentre;
    }

    /**
     * Deletes a gym centre based on the provided centre ID.
     * 
     * @param centreId The ID of the gym centre to be deleted.
     * @return boolean True if the gym centre is successfully deleted, otherwise false.
     */
    public boolean deleteGymCentre(int centreId) {
        System.out.println("Deleting Gym Centre:> ");
        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
        flipFitGymCentre.setCentreID(centreId);
        gymCentreDAO.deleteGymCentre(flipFitGymCentre);
        return true;
    }

    /**
     * Retrieves the list of available slots for a given gym centre.
     * 
     * @param centreId The ID of the gym centre for which available slots are to be viewed.
     * @return List<FlipFitSlots> A list of available slots for the specified gym centre.
     */
    public List<FlipFitSlots> viewAvailableSlots(int centreId) {
        System.out.println("Viewing Available Slots:> ");
        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
        flipFitGymCentre.setCentreID(centreId);
        return gymCentreDAO.viewAvailableSlots(flipFitGymCentre);
    }
}
