package com.flipkart.bean;

/**
 * Represents a FlipFit Gym Customer. 
 * Extends the AbstractUser class to inherit common user properties 
 * and adds additional attributes for managing customer-specific information such as payment details.
 */
public class FlipFitGymCustomer extends AbstractUser {

    // Represents the type of payment method used by the customer (e.g., Credit Card, PayPal, etc.)
    public int paymentType;

    // Holds the payment information related to the customer (e.g., credit card number or payment method details)
    public String paymentInfo;

    /**
     * Gets the payment type for the gym customer.
     * 
     * @return the payment type (e.g., Credit Card, PayPal, etc.).
     */
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type for the gym customer.
     * 
     * @param paymentType the payment type (e.g., Credit Card, PayPal, etc.) to set.
     */
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the payment information of the gym customer.
     * 
     * @return the payment information (e.g., credit card details or other payment method information).
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the payment information for the gym customer.
     * 
     * @param paymentInfo the payment information (e.g., credit card details or other payment method information) to set.
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
