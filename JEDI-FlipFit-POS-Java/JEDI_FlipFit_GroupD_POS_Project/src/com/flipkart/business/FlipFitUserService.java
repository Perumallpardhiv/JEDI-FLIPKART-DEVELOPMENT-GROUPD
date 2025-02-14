package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;

import java.util.*;

public class FlipFitUserService {

    private Map<String, FlipFitUser> users;
    private Set<FlipFitCustomer> customers;
    private Set<FlipFitGymOwner> gymOwners;

    public FlipFitUserService() {
        users = new HashMap<>();
        customers = new HashSet<>();
        gymOwners = new HashSet<>();

        users.put("john@example.com", new FlipFitCustomer("john@example.com", "password123", 1, "John Doe", "9876543210", 30, "New York"));
        users.put("alice@example.com", new FlipFitGymOwner("alice@example.com", "alicepass", 2, "Alice Smith", "9988776655", "123456789012", "ABCDE1234F"));
        users.put("admin@example.com", new FlipFitUser("admin@example.com", "admin123", 3));

        customers.add(new FlipFitCustomer("john@example.com", "password123", 1, "John Doe", "9876543210", 30, "New York"));
        gymOwners.add(new FlipFitGymOwner("alice@example.com", "alicepass", 2, "Alice Smith", "9988776655", "123456789012", "ABCDE1234F"));
    }

    public boolean registerCustomer(FlipFitCustomer flipFitCustomer) {
        if (users.containsKey(flipFitCustomer.getEmail())) {
            return false;
        }
        users.put(flipFitCustomer.getEmail(), flipFitCustomer);
        customers.add(flipFitCustomer);
        return true;
    }

    public boolean registerGymOwner(FlipFitGymOwner flipFitGymOwner) {
        if (users.containsKey(flipFitGymOwner.getEmail())) {
            return false;
        }
        users.put(flipFitGymOwner.getEmail(), flipFitGymOwner);
        gymOwners.add(flipFitGymOwner);
        return true;
    }

    public boolean authenticateUser(String email, String password) {
        FlipFitUser user = users.get(email);
        return user != null && user.getPassword().equals(password);
        boolean authenticateSuccess = false;
		authenticateSuccess = userDao.authenticateUser(user);
		return authenticateSuccess;
    }

    public boolean logout(String email) {
        return users.containsKey(email);
    }
}
