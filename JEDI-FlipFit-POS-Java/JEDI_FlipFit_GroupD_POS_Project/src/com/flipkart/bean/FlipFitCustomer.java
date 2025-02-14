package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlipFitCustomer extends FlipFitUser {
    private String name;
    private String phoneNumber;
    private int age;
    private String address;

    public FlipFitCustomer(String email, String password, int roleId, String name, String phoneNumber, int age, String address) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
    }

    public FlipFitCustomer() {
        super();
    }

    // Getter and Setter methods...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // A utility to simulate the collections of customers
    public static void main(String[] args) {
        // Create some hardcoded FlipFitCustomer instances
        FlipFitCustomer customer1 = new FlipFitCustomer("customer1@example.com", "password123", 1, "Alice Brown", "9876543210", 28, "123 Main St, City");
        FlipFitCustomer customer2 = new FlipFitCustomer("customer2@example.com", "password456", 2, "Bob White", "9876543211", 34, "456 Oak Ave, City");
        FlipFitCustomer customer3 = new FlipFitCustomer("customer3@example.com", "password789", 1, "Charlie Green", "9876543212", 22, "789 Pine Rd, City");

        // Hardcoded collections (ArrayList, HashSet, HashMap)

        // Using ArrayList to store multiple customers
        ArrayList<FlipFitCustomer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        // Using HashSet to track customers who have signed up
        Set<String> signedUpCustomers = new HashSet<>();
        signedUpCustomers.add(customer1.getPhoneNumber());
        signedUpCustomers.add(customer2.getPhoneNumber());

        // Using HashMap to map customers by email (unique identifier)
        Map<String, FlipFitCustomer> customerMap = new HashMap<>();
        customerMap.put(customer1.getEmail(), customer1);
        customerMap.put(customer2.getEmail(), customer2);
        customerMap.put(customer3.getEmail(), customer3);

        // Display customers from ArrayList
        System.out.println("Customer List:");
        for (FlipFitCustomer customer : customerList) {
            System.out.println(customer.getName() + " (" + customer.getPhoneNumber() + ")");
        }

        // Display signed-up customers from HashSet
        System.out.println("\nSigned Up Customers (by Phone Number):");
        for (String phone : signedUpCustomers) {
            System.out.println("Phone: " + phone);
        }

        // Access a customer using HashMap by email
        System.out.println("\nFetching Customer by Email (customer1@example.com):");
        FlipFitCustomer fetchedCustomer = customerMap.get("customer1@example.com");
        if (fetchedCustomer != null) {
            System.out.println("Customer Found: " + fetchedCustomer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
