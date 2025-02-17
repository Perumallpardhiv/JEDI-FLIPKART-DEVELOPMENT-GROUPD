package com.flipkart.business;

import com.flipkart.dao.FlipFitGymCustomerDAOImpl;
import com.flipkart.dao.interfaces.IFlipFitBookingDAO;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.dao.FlipFitBookingDAOImpl;
import com.flipkart.dao.FlipFitSlotDAOImpl;

/**
 * Handles the business logic for gym bookings in FlipFit.
 */
public class BookingsBusiness {

    private final IFlipFitBookingDAO bookingDAO;

    /**
     * Constructor to initialize the booking DAO.
     * 
     * @param FFBooking Booking DAO implementation.
     */
    public BookingsBusiness(FlipFitBookingDAOImpl FFBooking) {
        this.bookingDAO = FFBooking;
    }

    /**
     * Creates a booking for a user if the slot is available.
     * Resolves any booking conflicts by deleting existing bookings.
     * 
     * @param userID    User ID requesting the booking.
     * @param centreID  Gym centre ID.
     * @param startTime Slot start time.
     * @return FlipFitBooking Created booking or null if slot is unavailable.
     */
    public FlipFitBooking makeBooking(int userID, int centreID, int startTime) {
        FlipFitSlotDAOImpl slotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlots slotdetails = slotDAO.getSlotDetails(startTime, centreID);

        FlipFitGymCustomerDAOImpl FFGymCustomer = new FlipFitGymCustomerDAOImpl();
        FlipFitGymCustomerBusiness flipFitGymCustomerBusiness = new FlipFitGymCustomerBusiness(FFGymCustomer);

        if (slotdetails != null && slotdetails.getSeatsAvailable() > 0) {
            FlipFitBooking booking = flipFitGymCustomerBusiness.checkBookingConflicts(userID, startTime);
            if (booking != null) {
                deleteBooking(booking.getBookingId());
            }

            booking = new FlipFitBooking();
            booking.setSlotId(slotdetails.getSlotId());
            booking.setSlotTime(slotdetails.getSlotTime());
            booking.setUserId(userID);
            booking.setIsdeleted(false);

            bookingDAO.makeBooking(booking);

            int seatsAvailable = slotdetails.getSeatsAvailable();
            slotdetails.setSeatsAvailable(seatsAvailable - 1);

            FlipFitSlotsBusiness flipFitSlotsBusiness = new FlipFitSlotsBusiness();
            flipFitSlotsBusiness.updateAvailability(slotdetails);

            return booking;
        }
        return null;
    }

    /**
     * Deletes an existing booking by its ID and updates seat availability.
     * 
     * @param bookingId Booking ID to be deleted.
     * @return boolean True if deletion is successful, false if booking doesn't exist.
     */
    public boolean deleteBooking(int bookingId) {
        FlipFitBookingDAOImpl bookingDAO = new FlipFitBookingDAOImpl();
        FlipFitBooking bookingDetails = bookingDAO.getBookingDetailsByBookingId(bookingId);

        if (bookingDetails == null) {
            return false;
        }

        int slotId = bookingDetails.getSlotId();
        FlipFitSlotDAOImpl flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlots flipFitSlots = flipFitSlotDAO.getSlotDetailsById(slotId);

        if (flipFitSlots != null) {
            int seatsAvailable = flipFitSlots.getSeatsAvailable();
            flipFitSlots.setSeatsAvailable(seatsAvailable + 1);

            FlipFitSlotsBusiness flipFitSlotsBusiness = new FlipFitSlotsBusiness();
            flipFitSlotsBusiness.updateAvailability(flipFitSlots);
        }

        bookingDAO.deleteBooking(bookingId);
        return true;
    }
}
