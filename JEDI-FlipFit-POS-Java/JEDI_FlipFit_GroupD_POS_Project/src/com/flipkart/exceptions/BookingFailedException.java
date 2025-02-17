package com.flipkart.exceptions;

import static com.flipkart.constant.Constants.RED_COLOR;
import static com.flipkart.constant.Constants.RESET_COLOR;

/**
 * Exception thrown when Booking fails
 */
public class BookingFailedException extends RuntimeException{
    public BookingFailedException(String message){
        super(RED_COLOR+message+RESET_COLOR);
    }
}

