package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

public interface FlipFitGymOwnerServiceInterface {

    /**
     * @return The gym owner's profile as a FlipFitGymOwner object.
     */
    FlipFitGymOwner getProfile(String email);

    /**
     * @return void (No return value).
     */
    void editProfile(FlipFitGymOwner flipFitGymOwner, String email);

    /**
     * @return boolean indicating whether the gym was successfully added.
     */
    boolean addGym(FlipFitGym flipFitGym);

    /**
     * @return void (No return value).
     */
    void editGym(FlipFitGym flipFitGym);

    /**
     * @return A list of FlipFitGym objects representing the gyms owned by the owner.
     */
    List<FlipFitGym> getGymDetail(String gymOwnerEmail);

    /**
     * @return boolean indicating whether the gym owner is approved.
     */
    boolean isApproved(String email);

    /**
     * @return boolean indicating whether the gym is approved based on its gym ID.
     */
    boolean isGymApproved(String gymId);
}
