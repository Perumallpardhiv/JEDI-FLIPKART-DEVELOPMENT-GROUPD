package com.flipkart.business;

import com.flipkart.bean.*;
import java.util.*;

public interface FlipFitCustomerServiceInterface{
    public FlipFitCustomer getProfile(FlipFitCustomer FlipFitCustomer);
    /*
    returns the FlipFitCustomer profile
    */

    public void editProfile(FlipFitCustomer FlipFitCustomer);
    /*
    allows the FlipFitCustomer to edit profile
    */
    public List<FlipFitBooking> getBookings(String email);
    /*
    returns the list of all the bookings of the FlipFitCustomer
    */
    public boolean cancelBooking(String bookingId, String email);
    /*
    allows the FlipFitCustomer to cancel FlipFitBooking
    */

    public List<FlipFitGym> getGymInCity(String city);
    /*
    returns the list of gyms in a city
    */

    public List<FlipFitSlot> getSlotInGym(String gymId);
    /*
    returns the list of slots in a FlipFitGym
    */

    public int bookSlot(String gymId, String slotId, String email, Date date);
    /*
    allows the FlipFitCustomer to book a FlipFitSlot
    */

    public boolean isSlotBooked(String slotId, Date date);
    /*
    returns true if the FlipFitSlot is fully booked else returns false
    */

    public boolean hasBookedSlotAlready(String slotId, String customerEmail, Date date);
    /*
    checks if the FlipFitCustomer has already booked the FlipFitSlot with given slotId
    */
}