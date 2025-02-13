/**
 * 
 */
package com.flipkart.business;

import com.flipkart.bean.*;
import java.util.*;

public class FlipFitCustomerService {

	public void getProfile(FlipFitCustomer flipFitCustomer) {

	}

	public void editProfile(FlipFitCustomer flipFitCustomer) {

	}

	public List<FlipFitBooking> getBookings(String email) {
		return new ArrayList<>();
	}

	public boolean cancelBooking(String bookingId, String customerId) {
		return false;
	}

	public List<FlipFitGym> getGymInCity(String city) {
		return new ArrayList<>();
	}

	public List<FlipFitSlot> getSlotInGym(String gymId) {
		return new ArrayList<>();
	}

	public int bookSlot(String gymId, String slotId, String customerId, Date date) {
		return 2;
	}

	public boolean isSlotBooked(String slotId, Date date) {
		return false;
	}
	
	public boolean hasBookedSlotAlready(String slotId, String customerId, Date date) {
		return false;
	}


}
