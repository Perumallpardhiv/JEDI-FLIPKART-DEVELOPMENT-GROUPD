package com.flipkart.bean;

/**
 * Represents a FlipFit Gym Owner.
 * Extends the AbstractUser class to inherit common user properties and adds additional attributes
 * specific to gym owners such as PAN ID, GST number, Aadhar number, and approval status.
 */
public class FlipFitGymOwner extends AbstractUser {

    // Holds the PAN ID of the gym owner, used for taxation and legal purposes
    public String panId;

    // Holds the GST number of the gym owner, used for Goods and Services Tax identification
    public String gstNum;

    // Holds the Aadhar number of the gym owner, used for unique identity verification in India
    public String aadharNumber;

    // Indicates whether the gym owner is approved by the system (e.g., after verification)
    public boolean isApproved;

    /**
     * Gets the PAN ID of the gym owner.
     * 
     * @return the PAN ID of the gym owner.
     */
    public String getPanId() {
        return panId;
    }

    /**
     * Sets the PAN ID of the gym owner.
     * 
     * @param panId the PAN ID to set for the gym owner.
     */
    public void setPanId(String panId) {
        this.panId = panId;
    }

    /**
     * Gets the GST number of the gym owner.
     * 
     * @return the GST number of the gym owner.
     */
    public String getGSTNum() {
        return gstNum;
    }

    /**
     * Sets the GST number for the gym owner.
     * 
     * @param gstNum the GST number to set for the gym owner.
     */
    public void setGSTIN(String gstNum) {
        this.gstNum = gstNum;
    }

    /**
     * Gets the Aadhar number of the gym owner.
     * 
     * @return the Aadhar number of the gym owner.
     */
    public String getAadharNumber() {
        return aadharNumber;
    }

    /**
     * Sets the Aadhar number for the gym owner.
     * 
     * @param aadharNumber the Aadhar number to set for the gym owner.
     */
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    /**
     * Gets the approval status of the gym owner.
     * 
     * @return true if the gym owner is approved, false otherwise.
     */
    public boolean getIsApproved() {
        return isApproved;
    }

    /**
     * Sets the approval status of the gym owner.
     * 
     * @param isApproved true if the gym owner is approved, false otherwise.
     */
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
