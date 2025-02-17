package com.flipkart.dao;
import com.flipkart.dao.interfaces.IFlipFitBookingDAO;
import java.sql.*;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.constant.DBConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FlipFitBookingDAOImpl implements IFlipFitBookingDAO {

    // The method to create a new booking
    @Override
    public FlipFitBooking makeBooking(FlipFitBooking booking) {
        // SQL query to insert a new booking into the database
        String sql = "INSERT INTO Booking (userID, slotTime, slotID, isDeleted) VALUES (?, ?, ?, ?)";
        
        // Try-with-resources to handle the connection and statement automatically
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Set parameters for the prepared statement
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getSlotTime());
            stmt.setInt(3, booking.getSlotId());
            stmt.setBoolean(4, false);  // Default value for isDeleted is false
            
            // Execute the update (INSERT) query
            int affectedRows = stmt.executeUpdate(); // Execute the update query
            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            // Retrieve the generated booking ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int bookingID = generatedKeys.getInt(1);
                    booking.setBookingId(bookingID);  // Set the generated booking ID
                    booking.setIsdeleted(false);  // Set isDeleted as false
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions and print stack trace for debugging
            e.printStackTrace();
        }
        return booking; // Return the booking with its ID and status
    }

    // Method to delete a booking by its ID
    @Override
    public boolean deleteBooking(int bookingId) {
        // SQL query to delete a booking based on its booking ID
        String sql = "DELETE FROM Booking WHERE bookingID = ?";
        
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the booking ID for the prepared statement
            stmt.setInt(1, bookingId);

            // Execute the delete query
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting booking failed, no rows affected.");
            }
            return true;  // Return true if the deletion was successful
        } catch (SQLException e) {
            // Throw runtime exception if an error occurs during deletion
            throw new RuntimeException(e);
        }
    }
    
    private static final Logger LOGGER = Logger.getLogger(FlipFitBookingDAOImpl.class.getName());
    
    // Method to retrieve all bookings for a user based on their user ID
    @Override
    public List<FlipFitBooking> getAllBookings(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        String query = "SELECT bookingID, userID, slotID, isdeleted FROM Booking WHERE userID = ?";

        try {
            // Load MySQL driver (Not required in JDK 6+, kept for safety)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database using credentials from DBConstants
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL statement to fetch bookings for the given user ID
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);  // Set the user ID parameter

            // Execute the query and iterate through the result set
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Map the result set to a FlipFitBooking object
                    FlipFitBooking booking = new FlipFitBooking();
                    booking.setBookingId(rs.getInt("bookingID"));
                    booking.setUserId(rs.getInt("userID"));
                    booking.setSlotId(rs.getInt("slotID"));
                    booking.setIsdeleted(rs.getBoolean("isdeleted"));

                    // Add the booking to the list
                    bookings.add(booking);
                }
            }
            
        } catch (ClassNotFoundException e) {
            // Handle case where MySQL JDBC Driver is not found
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle any SQL errors during query execution
            System.err.println("Error retrieving bookings for userId: " + userId);
            e.printStackTrace(); // Consider using a logging framework
        }

        return bookings; // Return the list of bookings (may be empty if no bookings were found)
    }

    // Method to get booking details by booking ID
    @Override
    public List getBookingDetails(int bookingId) {
        List bookings = new ArrayList<>();
        
        try {
            // Load MySQL driver (Not required in JDK 6+, kept for safety)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL query to fetch a specific booking by its ID
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Booking WHERE id = ?");
            stmt.setInt(1, bookingId);

            // Execute the query and iterate through the result set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Map the result set to a FlipFitBooking object
                int UserId = rs.getInt("userId");
                int SlotId = rs.getInt("slotId");
                boolean IsDeleted = rs.getBoolean("isdeleted");

                FlipFitBooking booking = new FlipFitBooking();
                booking.setUserId(UserId);
                booking.setSlotId(SlotId);
                booking.setIsdeleted(IsDeleted);

                // Add the booking to the list
                bookings.add(booking);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error getting booking details: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException (should not occur if the driver is available)
            throw new RuntimeException(e);
        }
        
        return bookings; // Return the list of booking details (may be empty if no details found)
    }

    // Method to get a specific booking's details by booking ID
    public FlipFitBooking getBookingDetailsByBookingId(int bookingId) {
        FlipFitBooking booking = null;
        String sql = "SELECT * FROM Booking WHERE bookingID = ?";

        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the booking ID for the prepared statement
            stmt.setInt(1, bookingId);

            // Execute the query and retrieve the result set
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Map the result set to a FlipFitBooking object
                    booking = new FlipFitBooking();
                    booking.setBookingId(rs.getInt("bookingID"));
                    booking.setSlotId(rs.getInt("slotID"));
                    booking.setSlotTime(rs.getInt("slotTime"));
                    return booking;
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
        return null; // Return null if no booking was found with the given ID
    }
}
