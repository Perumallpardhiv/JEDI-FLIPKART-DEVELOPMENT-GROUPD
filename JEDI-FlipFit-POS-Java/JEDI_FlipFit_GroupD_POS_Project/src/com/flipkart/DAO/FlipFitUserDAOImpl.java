package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.flipkart.bean.*;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.utils.DBUtils;

public class FlipFitUserDAOImpl implements FlipFitUserDAO {

	public boolean authenticateUser(FlipFitUser user) {
		// to run without authentication, make isUserValid = true
		Connection connection = null;
		
		boolean isUserValid = false;
		try {connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select email, password, role from user where email = ?"); 

			preparedStatement.setString(1, user.getEmail());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (user.getPassword().equals(rs.getString("password"))
						&& user.getRoleId().equalsIgnoreCase(rs.getString("role"))) {
					System.out.println(
							rs.getString("email") + " " + rs.getString("password") + " " + rs.getString("role"));
					isUserValid = true;
				}
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return isUserValid;
	}

	public boolean registerCustomer(FlipFitCustomer customer) {
		Connection connection = null;
		boolean registerSuccess = false;
		String query = "INSERT INTO customer VALUES (?,?,?,?,?)";
		String queryUser = "INSERT INTO user VALUES (?,?,?)";
		try {connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);

			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getPhoneNumber());
			preparedStatement.setInt(4, customer.getAge());
			preparedStatement.setString(5, customer.getAddress());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

			preparedStatementUser.setString(1, customer.getEmail());
			preparedStatementUser.setString(2, customer.getPassword());
			preparedStatementUser.setString(3, "Customer");

			rowsAffected = preparedStatementUser.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

		} catch (SQLException e) {
			printSQLException(e);
		}

		return registerSuccess;
	}

	public boolean registerGymOwner(FlipFitGymOwner gymOwner) {
		Connection connection = null;
		boolean registerSuccess = false;
		String query = "INSERT INTO gymOwner VALUES (?,?,?,?,?,?)";
		String queryOwner = "INSERT INTO user VALUES (?,?,?)";
		try {connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);

			preparedStatement.setString(1, gymOwner.getEmail());
			preparedStatement.setString(2, gymOwner.getName());
			preparedStatement.setString(3, gymOwner.getPhoneNumber());
			preparedStatement.setString(4, gymOwner.getAadharNumber());
			preparedStatement.setString(5, gymOwner.getPanNumber());
			preparedStatement.setBoolean(6, gymOwner.isVerified());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

			preparedStatementOwner.setString(1, gymOwner.getEmail());
			preparedStatementOwner.setString(2, gymOwner.getPassword());
			preparedStatementOwner.setString(3, "GymOwner");

			rowsAffected = preparedStatementOwner.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

		} catch (SQLException e) {
			printSQLException(e);
		}

		return registerSuccess;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlipFitUserDAOImpl userDAO = new FlipFitUserDAOImpl();

        // Taking user input for registration
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        System.out.println("Enter Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Phone Number: ");
        String phone = scanner.nextLine();

        System.out.println("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Address: ");
        String address = scanner.nextLine();

        // Creating FlipFitCustomer object
        FlipFitCustomer customer = new FlipFitCustomer(email, password, "Customer", name, phone, age, address);

        // Registering the customer
        boolean isRegistered = userDAO.registerCustomer(customer);

        if (isRegistered) {
            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }

        scanner.close();
    }
}