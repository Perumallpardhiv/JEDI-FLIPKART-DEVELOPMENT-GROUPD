package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlipFitUserService {

    // Collections to store users
    private ArrayList<FlipFitCustomer> customersList = new ArrayList<>();
    private ArrayList<FlipFitGymOwner> gymOwnersList = new ArrayList<>();
    
    // Set to track active (authenticated) users (by email)
    private Set<String> authenticatedUsers = new HashSet<>();
    
    // Map to look up users quickly by email (unique identifier)
    private Map<String, FlipFitUser> userMap = new HashMap<>();

    // Register a new customer
    public boolean registerCustomer(FlipFitCustomer flipFitCustomer) {
        if (!userMap.containsKey(flipFitCustomer.getEmail())) {
            customersList.add(flipFitCustomer);
            userMap.put(flipFitCustomer.getEmail(), flipFitCustomer);
            return true;
        }
        return false; // User already exists
    }

    // Register a new gym owner
    public boolean registerGymOwner(FlipFitGymOwner flipFitGymOwner) {
        if (!userMap.containsKey(flipFitGymOwner.getEmail())) {
            gymOwnersList.add(flipFitGymOwner);
            userMap.put(flipFitGymOwner.getEmail(), flipFitGymOwner);
            return true;
        }
        return false; // User already exists
    }

    // Authenticate a user (based on email and password)
    public boolean authenticateUser(FlipFitUser flipFitUser) {
        FlipFitUser user = userMap.get(flipFitUser.getEmail());
        if (user != null && user.getPassword().equals(flipFitUser.getPassword())) {
            authenticatedUsers.add(flipFitUser.getEmail());
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }

    // Logout a user (remove from active session)
    public boolean logout(FlipFitUser flipFitUser) {
        if (authenticatedUsers.contains(flipFitUser.getEmail())) {
            authenticatedUsers.remove(flipFitUser.getEmail());
            return true; // Logout successful
        }
        return false; // User is not logged in
    }

    // Method to print details of all customers
    public void printCustomerList() {
        System.out.println("Customer List:");
        for (FlipFitCustomer customer : customersList) {
            System.out.println(customer.getName() + " (" + customer.getEmail() + ")");
        }
    }

    // Method to print details of all gym owners
    public void printGymOwnerList() {
        System.out.println("Gym Owner List:");
        for (FlipFitGymOwner gymOwner : gymOwnersList) {
            System.out.println(gymOwner.getName() + " (" + gymOwner.getEmail() + ")");
        }
    }

    // Method to check if a user is authenticated
    public boolean isUserAuthenticated(String email) {
        return authenticatedUsers.contains(email);
    }
    
    // Testing the functionality
    public static void main(String[] args) {
        // Create service instance
        FlipFitUserService userService = new FlipFitUserService();

        // Create sample users
        FlipFitCustomer customer1 = new FlipFitCustomer("customer1@example.com", "password123", 1, "Alice Brown", "9876543210", 28, "123 Main St, City");
        FlipFitGymOwner gymOwner1 = new FlipFitGymOwner("owner1@example.com", "password123", 1, "John Doe", "9876543210", "Aadhar1234", "PAN12345");

        // Register customers and gym owners
        userService.registerCustomer(customer1);
        userService.registerGymOwner(gymOwner1);

        // Authenticate customer
        boolean isCustomerAuthenticated = userService.authenticateUser(new FlipFitCustomer("customer1@example.com", "password123", 1, "", "", 0, ""));
        System.out.println("Customer authenticated: " + isCustomerAuthenticated);

        // Authenticate gym owner
        boolean isGymOwnerAuthenticated = userService.authenticateUser(new FlipFitGymOwner("owner1@example.com", "password123", 1, "", "", "", ""));
        System.out.println("Gym Owner authenticated: " + isGymOwnerAuthenticated);

        // Print customer and gym owner lists
        userService.printCustomerList();
        userService.printGymOwnerList();

        // Logout customer
        boolean isCustomerLoggedOut = userService.logout(customer1);
        System.out.println("Customer logged out: " + isCustomerLoggedOut);

        // Check if the customer is authenticated after logout
        System.out.println("Is customer authenticated after logout? " + userService.isUserAuthenticated("customer1@example.com"));
    }
}
