package com.flipkart.business;

public interface FlipFitBookingService {
	
    public boolean isConfirmed(String bookingId);
 
    public int getWaitingList();
}