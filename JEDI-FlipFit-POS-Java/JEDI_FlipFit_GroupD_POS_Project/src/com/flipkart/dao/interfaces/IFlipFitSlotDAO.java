package com.flipkart.dao.interfaces;

import com.flipkart.bean.FlipFitSlots;

import java.util.List;

public interface IFlipFitSlotDAO {

    /**
     * Adds a new slot to the system.
     * 
     * @param slot The slot to be added.
     * @return The added slot.
     */
    public FlipFitSlots addSlot(FlipFitSlots slot);

    /**
     * Deletes a slot based on its ID.
     * 
     * @param slotId The ID of the slot to delete.
     * @return True if the slot was deleted successfully, false otherwise.
     */
    public boolean deleteSlot(int slotId);

    /**
     * Updates the details of an existing slot.
     * 
     * @param slot The slot with updated information.
     * @return True if the slot was updated successfully, false otherwise.
     */
    public boolean changeSlot(FlipFitSlots slot);

    /**
     * Retrieves all slots available at a specific centre.
     * 
     * @param centreID The ID of the gym centre.
     * @return List of available slots at the specified centre.
     */
    public List<FlipFitSlots> getAllSlots(int centreID);

    /**
     * Fetches the details of a slot by its ID.
     * 
     * @param slotId The ID of the slot.
     * @return The slot details.
     */
    public FlipFitSlots getSlotDetailsById(int slotId);

    /**
     * Fetches the slot details based on the start time and centre ID.
     * 
     * @param startTime The start time of the slot.
     * @param centreID The ID of the gym centre.
     * @return The slot details.
     */
    public FlipFitSlots getSlotDetails(int startTime, int centreID);
}
