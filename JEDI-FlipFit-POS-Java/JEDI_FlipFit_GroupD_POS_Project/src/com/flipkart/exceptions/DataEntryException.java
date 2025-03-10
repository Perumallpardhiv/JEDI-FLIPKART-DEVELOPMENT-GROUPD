package com.flipkart.exceptions;

import static com.flipkart.constant.Constants.RED_COLOR;
import static com.flipkart.constant.Constants.YELLOW_COLOR;


/**
 * Exception thrown when incorrect data is entered
 */
public class DataEntryException extends RuntimeException{
    public DataEntryException(){
        super(RED_COLOR+"Incorrect Data format. Please refer to the format."+YELLOW_COLOR);
    }
}
