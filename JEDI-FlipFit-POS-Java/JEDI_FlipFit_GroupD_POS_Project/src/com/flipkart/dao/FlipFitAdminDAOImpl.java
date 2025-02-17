package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.dao.interfaces.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImpl implements IFlipFitAdminDAO {

    // Method to handle Admin login and validate credentials
    @Override
    public boolean adminLogin(FlipFitAdmin flipFitAdmin) {
        String sql = "SELECT * FROM GymAdmin WHERE emailId = ? AND password = ?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flipFitAdmin.getEmailID());
            stmt.setString(2, flipFitAdmin.getPassword());

            // Execute query and check if credentials are valid
            try (ResultSet rs = stmt.executeQuery()) {
                boolean res = rs.next();
                if (res) {
                    System.out.println("Logged in Successfully");
                } else {
                    System.out.println("Invalid Credentials!!!!");
                }
                return res;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Logged in Failed");
            return false;
        }
    }

    // Method to retrieve a list of pending gym owners who are not yet approved
    @Override
    public List<FlipFitGymOwner> getPendingOwnerList() {
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        String sql = "SELECT ownerID, aadharNumber FROM GymOwner WHERE approved = 0";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through the result set and populate pending owners
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("ownerID"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
                pendingOwners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingOwners;
    }

    // Method to retrieve a list of approved gym owners
    @Override
    public List<FlipFitGymOwner> getApprovedOwnerList() {
        List<FlipFitGymOwner> approvedOwners = new ArrayList<>();
        String sql = "SELECT * FROM GymOwner WHERE approved = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through the result set and populate approved owners
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("ownerID"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
                approvedOwners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return approvedOwners;
    }

    // Method to retrieve a list of all users
    @Override
    public List<FlipFitGymCustomer> getUserList() {
        List<FlipFitGymCustomer> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through the result set and populate user list
            while (rs.next()) {
                FlipFitGymCustomer user = new FlipFitGymCustomer();
                user.setUserId(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to validate and approve a gym owner
    @Override
    public boolean validateOwner(int ownerId) {
        String sql = "UPDATE GymOwner SET approved = 1 WHERE ownerID = ?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // Return true if update successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there's an error
        }
    }

    // Method to delete a gym owner record
    @Override
    public boolean deleteOwner(int ownerId) {
        String sql = "DELETE FROM GymOwner WHERE ownerID = ?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // Return true if deletion successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there's an error
        }
    }

    // Method to retrieve gym centres associated with a specific owner ID
    @Override
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId) {
        List<FlipFitGymCentre> gymCentres = new ArrayList<>();
        String sql = "SELECT * FROM GymCentre WHERE ownerID = ? AND approved = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ownerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitGymCentre gymCentre = new FlipFitGymCentre();
                    gymCentre.setCentreID(rs.getInt("centreID"));
                    gymCentre.setCapacity(rs.getInt("capacity"));
                    gymCentre.setCity(rs.getString("city"));
                    gymCentres.add(gymCentre); // Add gym centre to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gymCentres;
    }

    // Method to add a new admin to the system
    @Override
    public FlipFitAdmin addAdmin(FlipFitAdmin Admin) {
        String sql = "INSERT INTO GymAdmin (userID, emailID, password) VALUES (?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Admin.getUserID());
            stmt.setString(2, Admin.getEmailID());
            stmt.setString(3, Admin.getPassword());
            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating Admin failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Admin;
    }
}
