package com.flipkart.dao.interfaces;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;

import java.util.List;

/**
 * Interface for managing gym owners and their associated centers.
 */
public interface IFlipFitGymOwnerDAO {

    /**
     * Adds a new gym center to the system.
     * 
     * @param centre The gym center to be added.
     * @return The added gym center object.
     */
    public FlipFitGymCentre addCentre(FlipFitGymCentre centre);

    /**
     * Retrieves the list of gym centers owned by a specific gym owner.
     * 
     * @param owner The gym owner whose centers are to be fetched.
     * @return A list of gym centers owned by the specified owner.
     */
    public List<FlipFitGymCentre> viewCentresByOwnerID(FlipFitGymOwner owner);

    /**
     
     * 
     * @param centre The gym center whose customers are to be fetched.
     * @return A list of customers associated with the gym center.
     */
    // public List<FlipFitUser> viewFlipFitCustomers(FlipFitGymCentre centre);

    /**
     * Edits the details of a specific gym owner.
     * 
     * @param owner The gym owner whose details are to be edited.
     * @return The updated gym owner object.
     */
    public FlipFitGymOwner editDetails(FlipFitGymOwner owner);

    /**
     * Adds a new gym owner to the system, along with the associated user information.
     * 
     * @param owner The gym owner to be added.
     * @param user The associated user information for the gym owner.
     * @return The added gym owner object.
     */
    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user);

    /**
     * Adds a new user to the system.
     * 
     * @param user The user to be added.
     * @return The added user object.
     */
    public FlipFitUser addUser(FlipFitUser user);
}
