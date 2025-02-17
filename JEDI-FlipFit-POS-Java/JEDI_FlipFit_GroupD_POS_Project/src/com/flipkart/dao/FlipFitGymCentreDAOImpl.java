package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.constant.DBConstants;
import com.flipkart.bean.FlipFitSlots;

public class FlipFitGymCentreDAOImpl {

    // Random number generator for generating unique centre IDs
    Random rand = new Random();

    /**
     * Creates a new gym centre record in the database.
     * @param FFGC A FlipFitGymCentre object containing the details of the gym centre to be created.
     * @return The same FlipFitGymCentre object with the assigned centreID.
     */
    public FlipFitGymCentre createGymCentre(FlipFitGymCentre FFGC) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // SQL query to insert a new gym centre
            PreparedStatement stmt = con.prepareStatement("INSERT INTO GymCentre VALUES (?, ?, ?, ?, ?, ?, ?)");

            // Generate random centreID for the new gym centre
            FFGC.setCentreID(rand.nextInt(1000)); // Random ID between 0 and 999

            // Set the parameters for the SQL query
            stmt.setInt(1, FFGC.getCentreID());
            stmt.setInt(2, FFGC.getOwnerID());
            stmt.setInt(3, FFGC.getCapacity());
            stmt.setBoolean(4, FFGC.isApproved());
            stmt.setString(5, FFGC.getCity());
            stmt.setString(6, FFGC.getState());
            stmt.setString(7, FFGC.getPincode());

            // Execute the query and check if any rows were affected (successful insertion)
            int i = stmt.executeUpdate();
            System.out.println(i + " centre added");

            con.close(); // Close the database connection
        } catch (Exception e) {
            // Handle any exceptions (SQL or ClassNotFound)
            System.out.println(e);
        }
        return FFGC; // Return the FlipFitGymCentre object with the generated centreID
    }

    /**
     * Updates an existing gym centre record in the database.
     * @param FFGC A FlipFitGymCentre object containing the updated details of the gym centre.
     * @return The same FlipFitGymCentre object with updated details.
     */
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // SQL query to update an existing gym centre's details
            PreparedStatement stmt = con.prepareStatement("UPDATE GymCentre SET ownerID = ?, capacity = ?, approved = ?, city = ?, state = ?, pincode = ? WHERE centreID = ?");

            // Set parameters for the update query
            stmt.setInt(1, FFGC.getOwnerID());
            stmt.setInt(2, FFGC.getCapacity());
            stmt.setBoolean(3, FFGC.isApproved());
            stmt.setString(4, FFGC.getCity());
            stmt.setString(5, FFGC.getState());
            stmt.setString(6, FFGC.getPincode());
            stmt.setInt(7, FFGC.getCentreID());

            // Execute the update query
            int i = stmt.executeUpdate();
            System.out.println(i + " centre updated");

            con.close(); // Close the database connection
        } catch (Exception e) {
            // Handle any exceptions (SQL or ClassNotFound)
            System.out.println(e);
        }
        return FFGC; // Return the updated FlipFitGymCentre object
    }

    /**
     * Deletes a gym centre record from the database.
     * @param FFGC A FlipFitGymCentre object containing the centreID of the gym centre to be deleted.
     */
    public void deleteGymCentre(FlipFitGymCentre FFGC) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // SQL query to delete a gym centre by centreID
            PreparedStatement stmt = con.prepareStatement("DELETE FROM GymCentre WHERE centreID = ?");

            // Set the centreID parameter for the delete query
            stmt.setInt(1, FFGC.getCentreID());

            // Execute the delete query
            int i = stmt.executeUpdate();
            System.out.println(i + " centre deleted");

            con.close(); // Close the database connection
        } catch (Exception e) {
            // Handle any exceptions (SQL or ClassNotFound)
            System.out.println(e);
        }
    }

    /**
     * Retrieves a list of gym centres from a specific city.
     * @param city The name of the city for which the gym centres need to be fetched.
     * @return An ArrayList containing FlipFitGymCentre objects that match the city.
     */
    public ArrayList<FlipFitGymCentre> viewCentres(String city) {
        ArrayList<FlipFitGymCentre> ffarray = new ArrayList<FlipFitGymCentre>();
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // SQL query to fetch gym centres based on city
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM GymCentre WHERE city = ?");
            stmt.setString(1, city); // Set the city parameter for the query

            // Execute the query and iterate over the result set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Map the result set to a FlipFitGymCentre object
                FlipFitGymCentre FFGC = new FlipFitGymCentre();
                FFGC.setCentreID(rs.getInt("centreID"));
                FFGC.setOwnerID(rs.getInt("ownerID"));
                FFGC.setCapacity(rs.getInt("capacity"));
                FFGC.setApproved(rs.getBoolean("approved"));
                FFGC.setCity(rs.getString("city"));
                FFGC.setState(rs.getString("state"));
                FFGC.setPincode(rs.getString("pincode"));

                // Add the gym centre to the list
                ffarray.add(FFGC);
            }

            con.close(); // Close the database connection
        } catch (Exception e) {
            // Handle any exceptions (SQL or ClassNotFound)
            System.out.println(e);
        }
        return ffarray; // Return the list of gym centres
    }

    /**
     * Retrieves a list of available slots for a specific gym centre.
     * @param FFGC A FlipFitGymCentre object containing the centreID of the gym centre for which available slots are to be fetched.
     * @return An ArrayList containing FlipFitSlots objects representing the available slots for the gym centre.
     */
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC) {
        ArrayList<FlipFitSlots> ffarray = new ArrayList<FlipFitSlots>();

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // SQL query to fetch available slots with more than 0 seats for the specified gym centre
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Slots WHERE centreID = ? AND seatsAvailable > 0");
            stmt.setInt(1, FFGC.getCentreID()); // Set the centreID parameter for the query

            // Execute the query and iterate over the result set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Map the result set to a FlipFitSlots object
                FlipFitSlots FFS = new FlipFitSlots();
                FFS.setSlotId(rs.getInt("slotID"));
                FFS.setCentreId(rs.getInt("centreID"));
                FFS.setSeatsAvailable(rs.getInt("seatsAvailable"));
                FFS.setSlotTime(rs.getInt("slotTime"));

                // Add the slot to the list
                ffarray.add(FFS);
            }

            con.close(); // Close the database connection
        } catch (Exception e) {
            // Handle any exceptions (SQL or ClassNotFound)
            System.out.println(e);
        }
        return ffarray; // Return the list of available slots
    }
}
