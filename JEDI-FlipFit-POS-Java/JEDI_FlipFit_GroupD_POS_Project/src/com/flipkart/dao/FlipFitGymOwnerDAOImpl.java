package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.interfaces.IFlipFitGymOwnerDAO;
import java.util.Random;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.FlipFitUser;

public class FlipFitGymOwnerDAOImpl implements IFlipFitGymOwnerDAO {
    Random rand = new Random();

    /**
     * Adds a new gym centre to the database.
     * @param centre The FlipFitGymCentre object containing the details of the gym centre.
     * @return The updated FlipFitGymCentre object with the generated Centre ID.
     */
    @Override
    public FlipFitGymCentre addCentre(FlipFitGymCentre centre) {
        String sql = "INSERT INTO GymCentre (ownerID, capacity, approved, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set the parameters for the prepared statement
            stmt.setInt(1, centre.getOwnerID());
            stmt.setInt(2, centre.getCapacity());
            stmt.setBoolean(3, centre.isApproved());
            stmt.setString(4, centre.getCity());
            stmt.setString(5, centre.getState());
            stmt.setString(6, centre.getPincode());

            // Execute the insert statement and check if a row was affected
            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating centre failed, no rows affected.");
            }

            // Retrieve the generated Centre ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int centreID = generatedKeys.getInt(1);
                    centre.setCentreID(centreID);
                } else {
                    throw new SQLException("Creating centre failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centre;
    }

    /**
     * Retrieves all gym centres associated with a specific gym owner.
     * @param owner The FlipFitGymOwner object containing the details of the gym owner.
     * @return A list of FlipFitGymCentre objects associated with the owner.
     */
    @Override
    public List<FlipFitGymCentre> viewCentresByOwnerID(FlipFitGymOwner owner) {
        List<FlipFitGymCentre> gymcentres = new ArrayList<>();
        int userId = owner.getUserId();
        String sql = "SELECT centreID, ownerID, capacity, city, state, pincode FROM GymCentre where ownerID=?";
        
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);  // Set the owner ID in the query
            
            // Execute the query and process the result set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FlipFitGymCentre gymcentre = new FlipFitGymCentre();
                gymcentre.setCentreID(rs.getInt("centreID"));
                gymcentre.setOwnerID(rs.getInt("ownerID"));
                gymcentre.setCapacity(rs.getInt("capacity"));
                gymcentre.setCity(rs.getString("city"));
                gymcentre.setState(rs.getString("state"));
                gymcentre.setPincode(rs.getString("pincode"));
                gymcentres.add(gymcentre);  // Add each gym centre to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gymcentres;
    }

    /**
     * Updates the details of an existing gym owner (e.g., PAN, Aadhar number, GSTIN).
     * @param owner The FlipFitGymOwner object with updated details.
     * @return The updated FlipFitGymOwner object, or null if the update fails.
     */
    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner owner) {
        int userId = owner.getUserId();
        String sql = "UPDATE GymOwner SET PAN=?, aadharNumber=?, GSTIN=? WHERE ownerID=?";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, owner.getPanId());
            stmt.setString(2, owner.getAadharNumber());
            stmt.setString(3, owner.getGSTNum());
            stmt.setInt(4, userId);

            // Execute the update and check if any rows were affected
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return owner;  // Return the updated owner details
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Return null if update fails
    }

    /**
     * Adds a new user to the system.
     * @param user The FlipFitUser object containing the details of the user.
     * @return The updated FlipFitUser object with the generated User ID.
     */
    @Override
    public FlipFitUser addUser(FlipFitUser user) {
        String sql = "INSERT INTO User (userName, roleID, emailID, phoneNumber, password) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, user.getUserName());
            stmt.setInt(2, user.getRoleID());
            stmt.setString(3, user.getEmailID());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getPassword());

            // Execute the insert statement and check if a row was affected
            int affectedRows = stmt.executeUpdate();  // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // Retrieve the generated User ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);
                    user.setUserID(userID);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;  // Return the user with the generated ID
    }

    /**
     * Adds a new gym owner to the system by creating both a FlipFitGymOwner and a corresponding FlipFitUser.
     * @param owner The FlipFitGymOwner object with details to be added.
     * @param user The FlipFitUser object representing the owner.
     * @return The updated FlipFitGymOwner object with the generated User ID.
     */
    @Override
    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user) {
        String sql = "INSERT INTO GymOwner (ownerID, PAN, aadharNumber, GSTIN, approved) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the parameters for the prepared statement
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, owner.getPanId());
            stmt.setString(3, owner.getAadharNumber());
            stmt.setString(4, owner.getGSTNum());
            stmt.setBoolean(5, false);  // Set the approval status to false initially

            // Execute the insert statement and check if a row was affected
            int affectedRows = stmt.executeUpdate();  // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating owner failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Set the generated user ID for the owner and return the updated owner
        owner.setUserId(user.getUserID());
        return owner;
    }
}
