package com.flipkart.dao.interfaces;

import com.flipkart.bean.FlipFitPayments;

/**
 * Interface for payment operations.
 */
public interface IFlipFitPaymentsDAO {

    /**
     * Saves the payment information for a user.
     * 
     * @param FFP The payment details to save.
     */
    public void setPaymentInfo(FlipFitPayments FFP);

    /**
     * Deletes the payment information for a user.
     * 
     * @param FFP The payment details to delete.
     */
    public void deletePaymentInfo(FlipFitPayments FFP);
}
