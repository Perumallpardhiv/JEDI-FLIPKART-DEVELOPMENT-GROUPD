package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.dao.interfaces.IFlipFitGymCustomerDAO;

import java.util.*;
import java.sql.*;

/**
 * Implementation class for the IFlipFitGymCustomerDAO interface. 
 * This class provides the logic to interact with the database for various gym customer-related operations.
 */
public class FlipFitGymCustomerDAOImpl implements IFlipFitGymCustomerDAO {

    /**
     * Retrieves all the booked slots for a specific user.
     * @param userID The ID of the user for whom the booked slots are to be fetched.
     * @return A List of FlipFitSlots representing the booked slots for the user.
     */
    public List<FlipFitSlots> viewBookedSlots(int userID) {
        List<FlipFitSlots> bookedSlots = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE userID = ? and isDeleted=FALSE";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);  // Set the userID parameter for the query
            ResultSet rs = stmt.executeQuery();  // Execute the query to fetch booked slots

            while (rs.next()) {
                // Map the result set to FlipFitSlots object
                FlipFitSlots slot = new FlipFitSlots();
                slot.setSlotId(rs.getInt("slotID"));
                bookedSlots.add(slot);  // Add the booked slot to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }

        return bookedSlots;  // Return the list of booked slots
    }

    /**
     * Checks if there are any booking conflicts for a user at a specific slot time.
     * @param userId The ID of the user.
     * @param slotTime The time of the slot to check for conflicts.
     * @return A FlipFitBooking object representing the conflicting booking, if any; otherwise null.
     */
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime) {
        String sql = "SELECT * FROM Booking WHERE userID = ? and slotTime = ?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);  // Set the userId parameter
            stmt.setInt(2, slotTime);  // Set the slotTime parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If a conflict is found, map the result to FlipFitBooking
                    FlipFitBooking booking = new FlipFitBooking();
                    booking.setSlotTime(rs.getInt("slotTime"));
                    booking.setSlotId(rs.getInt("slotID"));
                    booking.setUserId(userId);
                    booking.setBookingId(rs.getInt("bookingID"));
                    booking.setIsdeleted(rs.getBoolean("isDeleted"));
                    return booking;  // Return the conflicting booking
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }
        return null;  // Return null if no conflict is found
    }

    /**
     * Retrieves a list of all gym centres.
     * @return A List of FlipFitGymCentre objects representing all gym centres in the database.
     */
    public List<FlipFitGymCentre> viewCentres() {
        List<FlipFitGymCentre> gymcentres = new ArrayList<>();
        String sql = "SELECT * FROM GymCentre";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();  // Execute the query to fetch gym centres

            while (rs.next()) {
                // Map the result set to FlipFitGymCentre object
                FlipFitGymCentre gymcentre = new FlipFitGymCentre();
                gymcentre.setCentreID(rs.getInt("centreID"));
                gymcentre.setOwnerID(rs.getInt("ownerID"));
                gymcentre.setCapacity(rs.getInt("capacity"));
                gymcentre.setCity(rs.getString("city"));
                gymcentre.setPincode(rs.getString("pincode"));
                gymcentres.add(gymcentre);  // Add the gym centre to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }

        return gymcentres;  // Return the list of gym centres
    }

    /**
     * Makes a payment for a user. (Currently not implemented)
     * @param userID The ID of the user making the payment.
     * @return A boolean indicating whether the payment was successful.
     */
    public boolean makePayment(int userID) {
        // Payment logic is not yet implemented.
        return false;
    }

    /**
     * Retrieves payment details for a user. (Currently not implemented)
     * @param userID The ID of the user whose payment details are to be fetched.
     */
    public void viewPaymentDetails(int userID) {
        // Payment details viewing logic is not yet implemented.
    }

    /**
     * Edits the payment details for a user. (Currently not implemented)
     * @param userID The ID of the user whose payment details are to be edited.
     */
    public void editPaymentDetails(int userID) {
        // Payment details editing logic is not yet implemented.
    }

    /**
     * Edits the details of a customer (city and pincode) and updates user information (username and password).
     * @param customer A FlipFitGymCustomer object containing updated details for the customer.
     * @return The updated FlipFitGymCustomer object if the update is successful; null otherwise.
     */
    public FlipFitGymCustomer editDetails(FlipFitGymCustomer customer) {
        String sql = "UPDATE Customer SET city=?, pincode=? WHERE customerID=?";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCity());  // Set the updated city
            stmt.setString(2, customer.getPinCode());  // Set the updated pincode
            stmt.setInt(3, customer.getUserId());  // Set the userId of the customer
            int rowsAffected = stmt.executeUpdate();  // Execute the update query
            if (rowsAffected == 0) {
                return null;  // Return null if no rows were affected
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }

        // Update user information
        sql = "UPDATE User SET userName=?, password=? WHERE userID=?";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getUserName());  // Set the updated username
            stmt.setString(2, customer.getPassword());  // Set the updated password
            stmt.setInt(3, customer.getUserId());  // Set the userId of the customer
            int rowsAffected = stmt.executeUpdate();  // Execute the update query
            if (rowsAffected == 0) {
                return null;  // Return null if no rows were affected
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }

        return customer;  // Return the updated customer
    }

    /**
     * Adds a new user to the database.
     * @param user A FlipFitUser object containing the user details to be added.
     * @return The FlipFitUser object with the generated userID.
     */
    public FlipFitUser addUser(FlipFitUser user) {
        String sql = "INSERT INTO User (userName, roleID, emailID, phoneNumber, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUserName());
            stmt.setInt(2, user.getRoleID());
            stmt.setString(3, user.getEmailID());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getPassword());

            int affectedRows = stmt.executeUpdate();  // Execute the query to insert the user
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);  // Retrieve the generated userID
                    user.setUserID(userID);  // Set the userID for the user
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }
        return user;  // Return the user object with the generated userID
    }

    /**
     * Adds a new customer to the database and associates them with a user.
     * @param customer A FlipFitGymCustomer object containing the customer details.
     * @param user A FlipFitUser object containing the user details.
     * @return The FlipFitGymCustomer object with the assigned userID.
     */
    public FlipFitGymCustomer addCustomer(FlipFitGymCustomer customer, FlipFitUser user) {
        String sql = "INSERT INTO Customer (customerID, city, pincode) VALUES (?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserID());  // Set the customerID to the userID
            stmt.setString(2, customer.getCity());  // Set the city for the customer
            stmt.setString(3, customer.getPinCode());  // Set the pincode for the customer

            int affectedRows = stmt.executeUpdate();  // Execute the query to insert the customer
            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        }
        customer.setUserId(user.getUserID());  // Set the userId for the customer
        return customer;  // Return the customer object with the assigned userID
    }
}
