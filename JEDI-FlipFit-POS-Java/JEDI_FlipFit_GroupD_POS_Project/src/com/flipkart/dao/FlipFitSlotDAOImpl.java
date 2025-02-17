package com.flipkart.dao;

import com.flipkart.dao.interfaces.*;
import java.sql.*;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.constant.DBConstants;
import java.util.ArrayList;
import java.util.List;

public class FlipFitSlotDAOImpl implements IFlipFitSlotDAO {

    /**
     * Adds a new slot to the Slots table in the database.
     * @param slot The FlipFitSlots object containing the details of the slot to be added.
     * @return The added FlipFitSlots object with the generated slot ID.
     */
    @Override
    public FlipFitSlots addSlot(FlipFitSlots slot) {
        String sql = "INSERT INTO Slots (centreID, slotTime, seatsAvailable) VALUES (?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Set the parameters for the SQL query
            stmt.setInt(1, slot.getCentreId());
            stmt.setInt(2, slot.getSlotTime());
            stmt.setInt(3, slot.getSeatsAvailable());

            // Execute the query to insert the slot
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating slot failed, no rows affected.");
            }

            // Retrieve the generated slot ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    slot.setSlotId(generatedKeys.getInt(1));  // Set the generated slot ID
                } else {
                    throw new SQLException("Creating slot failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding slot: " + e);
        }
        return slot;
    }

    /**
     * Deletes a slot based on the provided slot ID.
     * @param slotID The ID of the slot to be deleted.
     * @return true if the slot was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteSlot(int slotID) {
        try {
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL statement to delete the slot
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Slots WHERE slotId = ?");
            stmt.setInt(1, slotID);

            // Execute the delete query and check the result
            int i = stmt.executeUpdate();
            System.out.println(i + " slot deleted");

            con.close();
            return i > 0;

        } catch (Exception e) {
            System.out.println("Error deleting slot: " + e);
        }
        return false;
    }

    /**
     * Updates the details of an existing slot.
     * @param slot The FlipFitSlots object containing the updated details of the slot.
     * @return true if the slot was successfully updated, false otherwise.
     */
    @Override
    public boolean changeSlot(FlipFitSlots slot) {
        String sql = "UPDATE Slots SET centreID = ?, slotTime = ?, seatsAvailable = ? WHERE slotID = ?";
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the parameters for the SQL update query
            stmt.setInt(1, slot.getCentreId());
            stmt.setInt(2, slot.getSlotTime());
            stmt.setInt(3, slot.getSeatsAvailable());
            stmt.setInt(4, slot.getSlotId());

            // Execute the update and check the result
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating slots failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Retrieves all slots for a given center ID.
     * @param centreID The ID of the center for which to retrieve the slots.
     * @return A list of FlipFitSlots objects representing the slots available for the specified center.
     */
    @Override
    public List<FlipFitSlots> getAllSlots(int centreID) {
        List<FlipFitSlots> slots = new ArrayList<>();
        try {
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL query to retrieve slots by centre ID
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Slots WHERE centreID = ?");
            stmt.setInt(1, centreID);

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int slotID = rs.getInt("slotID");
                int slotTime = rs.getInt("slotTime");
                int seatsAvailable = rs.getInt("seatsAvailable");

                // Create and populate the FlipFitSlots object
                FlipFitSlots slot = new FlipFitSlots();
                slot.setSlotId(slotID);
                slot.setCentreId(centreID);
                slot.setSlotTime(slotTime);
                slot.setSeatsAvailable(seatsAvailable);
                slots.add(slot);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving slots: " + e.getMessage());
        }
        return slots;
    }

    /**
     * Retrieves the details of a specific slot by its ID.
     * @param slotId The ID of the slot whose details are to be retrieved.
     * @return The FlipFitSlots object containing the details of the specified slot, or null if not found.
     */
    @Override
    public FlipFitSlots getSlotDetailsById(int slotId) {
        String sql = "SELECT * FROM Slots WHERE slotID=?";
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);

            // Execute the query and retrieve the result
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FlipFitSlots slot = new FlipFitSlots();
                    slot.setSlotId(rs.getInt("slotID"));
                    slot.setSlotTime(rs.getInt("slotTime"));
                    slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                    slot.setCentreId(rs.getInt("centreID"));

                    return slot;
                } else {
                    throw new SQLException("Slot details not found for slotID = " + slotId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the details of a slot based on the start time and center ID.
     * @param startTime The start time of the slot.
     * @param centreID The ID of the center for which to retrieve the slot.
     * @return The FlipFitSlots object containing the details of the slot, or null if not found.
     */
    @Override
    public FlipFitSlots getSlotDetails(int startTime, int centreID) {
        String sql = "SELECT * FROM Slots WHERE slotTime = ? AND centreID = ?";
        try (Connection conn = GetConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, startTime);
            stmt.setInt(2, centreID);

            // Execute the query and retrieve the result
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FlipFitSlots slot = new FlipFitSlots();
                    slot.setSlotId(rs.getInt("slotID"));
                    slot.setSlotTime(startTime);
                    slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                    slot.setCentreId(centreID);

                    return slot;
                } else {
                    throw new SQLException("Slot details not found for startTime = " + startTime + " and centreID = " + centreID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
