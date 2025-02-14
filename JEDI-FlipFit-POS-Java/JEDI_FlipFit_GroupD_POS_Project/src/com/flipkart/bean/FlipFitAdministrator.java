package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlipFitAdministrator extends FlipFitUser {
    private String name;
    private String phoneNumber;

    public FlipFitAdministrator(String email, String password, int roleId, String name, String phoneNumber) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public FlipFitAdministrator() {
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

    // A utility to simulate the collections of administrators
    public static void main(String[] args) {
        // Create some hardcoded FlipFitAdministrator instances
        FlipFitAdministrator admin1 = new FlipFitAdministrator("admin1@example.com", "password123", 1, "Emily Davis", "9876543210");
        FlipFitAdministrator admin2 = new FlipFitAdministrator("admin2@example.com", "password456", 2, "Michael Johnson", "9876543211");
        FlipFitAdministrator admin3 = new FlipFitAdministrator("admin3@example.com", "password789", 1, "Sarah Lee", "9876543212");

        // Hardcoded collections (ArrayList, HashSet, HashMap)

        // Using ArrayList to store multiple administrators
        ArrayList<FlipFitAdministrator> adminList = new ArrayList<>();
        adminList.add(admin1);
        adminList.add(admin2);
        adminList.add(admin3);

        // Using HashSet to track administrators by phone number (ensuring uniqueness)
        Set<String> adminPhoneNumbers = new HashSet<>();
        adminPhoneNumbers.add(admin1.getPhoneNumber());
        adminPhoneNumbers.add(admin2.getPhoneNumber());

        // Using HashMap to map administrators by email (unique identifier)
        Map<String, FlipFitAdministrator> adminMap = new HashMap<>();
        adminMap.put(admin1.getEmail(), admin1);
        adminMap.put(admin2.getEmail(), admin2);
        adminMap.put(admin3.getEmail(), admin3);

        // Display administrators from ArrayList
        System.out.println("Administrator List:");
        for (FlipFitAdministrator admin : adminList) {
            System.out.println(admin.getName() + " (" + admin.getPhoneNumber() + ")");
        }

        // Display administrators by phone number from HashSet
        System.out.println("\nAdministrators (by Phone Number):");
        for (String phone : adminPhoneNumbers) {
            System.out.println("Phone: " + phone);
        }

        // Access an administrator using HashMap by email
        System.out.println("\nFetching Administrator by Email (admin1@example.com):");
        FlipFitAdministrator fetchedAdmin = adminMap.get("admin1@example.com");
        if (fetchedAdmin != null) {
            System.out.println("Administrator Found: " + fetchedAdmin.getName());
        } else {
            System.out.println("Administrator not found.");
        }
    }
}
