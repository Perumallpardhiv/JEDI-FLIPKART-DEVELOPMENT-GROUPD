package com.flipkart.exceptions;

import static com.flipkart.constant.Constants.RED_COLOR;
import static com.flipkart.constant.Constants.RESET_COLOR;

/**
 * Exception thrown when User is not found
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message){
        super(message);
        System.out.println(RED_COLOR+"User not found!!"+RESET_COLOR);
    }
}