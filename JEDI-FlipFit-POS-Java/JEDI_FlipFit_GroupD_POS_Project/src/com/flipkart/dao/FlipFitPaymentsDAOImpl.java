package com.flipkart.dao;

import com.flipkart.bean.FlipFitPayments;
import com.flipkart.constant.DBConstants;
import com.flipkart.dao.interfaces.IFlipFitPaymentsDAO;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FlipFitPaymentsDAOImpl implements IFlipFitPaymentsDAO {
    Random rand = new Random();

    /**
     * Sets or updates payment information for a user.
     * This method uses the REPLACE SQL query to insert new payment info or update existing info 
     * based on the userID (it works like an INSERT or UPDATE depending on the presence of a row with the same userID).
     * @param FFP The FlipFitPayments object containing the payment details (userID, paymentType, paymentInfo).
     */
    @Override
    public void setPaymentInfo(FlipFitPayments FFP) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database using DBConstants for credentials
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL statement for REPLACE operation to insert or update payment info
            PreparedStatement stmt = con.prepareStatement("REPLACE INTO Payments VALUES (?, ?, ?)");

            // Set parameters for the query using the FlipFitPayments object
            stmt.setInt(1, FFP.getUserID());         // User ID
            stmt.setInt(2, FFP.getPaymentType());    // Payment type (e.g., 1 for card, 2 for UPI, etc.)
            stmt.setString(3, FFP.getPaymentInfo()); // Payment info (e.g., account number, UPI ID)

            // Execute the query and output the number of affected rows (i.e., successful insert/update)
            int i = stmt.executeUpdate();
            System.out.println(i + " payment info added");

            con.close();
        } catch (Exception e) {
            // Print any exceptions to the console for debugging purposes
            System.out.println(e);
        }
    }

    /**
     * Deletes the payment information for a user based on their userID.
     * This method removes the payment info associated with the provided userID.
     * @param FFP The FlipFitPayments object containing the userID for which payment info should be deleted.
     */
    @Override
    public void deletePaymentInfo(FlipFitPayments FFP) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database using DBConstants for credentials
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            // Prepare the SQL statement to delete the payment info for a specific user based on userID
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Payments WHERE userID=(?)");

            // Set the userID parameter for the query
            stmt.setInt(1, FFP.getUserID());

            // Execute the delete query and output the number of rows affected (successful deletions)
            int i = stmt.executeUpdate();
            System.out.println(i + " payment info deleted");

            // Close the database connection
            con.close();
        } catch (Exception e) {
            // Print any exceptions to the console for debugging purposes
            System.out.println(e);
        }
    }
}
