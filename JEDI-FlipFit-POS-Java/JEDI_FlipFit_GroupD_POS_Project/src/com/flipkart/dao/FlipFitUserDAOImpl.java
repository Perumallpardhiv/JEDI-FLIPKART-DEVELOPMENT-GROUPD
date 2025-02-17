package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.constant.DBConstants;
import com.flipkart.dao.interfaces.*;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitUser;

import java.sql.*;
import java.util.Random;

public class FlipFitUserDAOImpl implements IFlipFitUserDAO {
    Random rand = new Random(); // Random instance for generating user IDs

    /**
     * Login as a customer by verifying email and password.
     * @param emailID The email ID of the user.
     * @param password The password of the user.
     * @return The user object if login is successful, otherwise null.
     */
    @Override
    public FlipFitUser loginAsCustomer(String emailID, String password) {
        String sql = "SELECT * from User where emailID=? and password=? and roleID=1"; // SQL query to find customer
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emailID);  
            stmt.setString(2, password); 

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    FlipFitUser flipFitUser = new FlipFitUser();
                    flipFitUser.setEmailID(emailID);
                    flipFitUser.setPassword(password);
                    flipFitUser.setUserID(rs.getInt("userID"));
                    flipFitUser.setRoleID(rs.getInt("roleID"));
                    flipFitUser.setUserName(rs.getString("userName"));
                    return flipFitUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    /**
     * Login as an owner by verifying email and password.
     * @param emailID The email ID of the user.
     * @param password The password of the user.
     * @return The user object if login is successful, otherwise null.
     */
    @Override
    public FlipFitUser loginAsOwner(String emailID, String password) {
        String sql = "SELECT * from User where emailID=? and password=? and roleID=2"; // SQL query to find owner
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emailID);  
            stmt.setString(2, password);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    FlipFitUser flipFitUser = new FlipFitUser();
                    flipFitUser.setEmailID(emailID);
                    flipFitUser.setPassword(password);
                    flipFitUser.setUserID(rs.getInt("userID"));
                    flipFitUser.setRoleID(rs.getInt("roleID"));
                    flipFitUser.setUserName(rs.getString("userName"));
                    return flipFitUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    /**
     * Add a new user to the database with a random user ID.
     * @param FFU The FlipFitUser object containing user details (userID, userName, emailID, etc.).
     */
    @Override
    public void addUser(FlipFitUser FFU) {
        try{
            // Register the JDBC driver and open a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?, ?, ?)");

            FFU.setUserID(rand.nextInt(1000)); // Generate a random userID
            stmt.setInt(1, FFU.getUserID());
            stmt.setInt(2, FFU.getUserID()); // Assign the generated userID to both userID and the second column (for unique identifier)
            stmt.setInt(3, FFU.getRoleID());
            stmt.setString(5, FFU.getPhoneNumber());
            stmt.setString(4, FFU.getEmailID());
            stmt.setString(6, FFU.getPassword());

            int i = stmt.executeUpdate(); // Execute the insert query
            System.out.println(i + " user added"); // Log the successful addition
            con.close(); // Close the connection
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle any exceptions
        }
    }

    /**
     * Delete a user from the database by userID.
     * @param FFU The FlipFitUser object containing the userID to be deleted.
     */
    @Override
    public void deleteUser(FlipFitUser FFU) {
        try{
            // Register the JDBC driver and open a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);
            PreparedStatement stmt = con.prepareStatement("DELETE FROM User WHERE userID=(?)");

            stmt.setInt(1, FFU.getUserID()); // Set the userID parameter to delete the specific user

            int i = stmt.executeUpdate(); // Execute the delete query
            System.out.println(i + " user removed"); // Log the successful deletion
            con.close(); // Close the connection
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle any exceptions
        }
    }

    /**
     * Update a user's details in the database based on userID.
     * @param FFU The FlipFitUser object containing the updated user details.
     */
    @Override
    public void changeUser(FlipFitUser FFU) {
        try{
            // Register the JDBC driver and open a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);
            PreparedStatement stmt = con.prepareStatement("UPDATE User SET userName = ?, roleID = ?, emailId = ?, phoneNumber = ?, password = ? WHERE userID = ?");
            
            stmt.setString(1, FFU.getUserName());
            stmt.setInt(2, FFU.getRoleID());
            stmt.setString(3, FFU.getEmailID());
            stmt.setString(4, FFU.getPhoneNumber());
            stmt.setString(5, FFU.getPassword());
            stmt.setInt(6, FFU.getUserID()); // Use the userID to specify the record to update

            int i = stmt.executeUpdate(); // Execute the update query
            System.out.println(i + " user changed"); // Log the successful update
            con.close(); // Close the connection
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle any exceptions
        }
    }

    /**
     * Fetch the details of a user by their userID.
     * @param userID The ID of the user whose details are to be fetched.
     * @return The FlipFitUser object containing the user's details.
     */
    @Override
    public FlipFitUser getUser(int userID) {
        FlipFitUser FFU = new FlipFitUser(); // Create a new FlipFitUser object to store fetched details
        try {
            // Register the JDBC driver and open a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE userId = ?");
            stmt.setInt(1, userID); // Set the userID parameter to fetch the corresponding user

            ResultSet rs = stmt.executeQuery(); // Execute the query and retrieve the results
            if(rs.next()) {
                FFU.setUserName(rs.getString("userName"));
                FFU.setUserID(rs.getInt("userID"));
                FFU.setPassword(rs.getString("password"));
                FFU.setPhoneNumber(rs.getString("phoneNumber"));
                FFU.setRoleID(rs.getInt("roleID"));
                FFU.setEmailID(rs.getString("emailId"));
            }

            con.close(); // Close the connection
        } catch (Exception e) {
            System.out.println(e); // Handle any exceptions
        }
        return FFU; // Return the populated FlipFitUser object
    }
}
