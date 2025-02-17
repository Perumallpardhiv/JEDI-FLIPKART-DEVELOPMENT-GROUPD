package com.flipkart.bean;

public class FlipFitPayments {

    // User ID of the person making the payment
    private int userID;

    // Payment type (1 for UPI, 2 for Debit Card)
    private int paymentType;

    // Payment information (e.g., transaction ID or payment reference)
    private String paymentInfo;

    /**
     * Gets the payment information.
     * 
     * @return the payment information (e.g., transaction ID).
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the payment information.
     * 
     * @param paymentInfo the payment information to set.
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    /**
     * Gets the payment type.
     * 
     * @return the payment type (1 for UPI, 2 for Debit Card).
     */
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     * 
     * @param paymentType the payment type to set.
     */
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID associated with the payment.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     * 
     * @param userID the user ID to associate with the payment.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
