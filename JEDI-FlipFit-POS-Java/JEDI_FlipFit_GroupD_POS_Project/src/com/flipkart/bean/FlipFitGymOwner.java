package com.flipkart.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlipFitGymOwner extends FlipFitUser {
    private String name;
    private String phoneNumber;
    private String aadharNumber;
    private String panNumber;
    private boolean isVerified;

    public FlipFitGymOwner(String email, String password, int roleId, String name, String phoneNumber, String aadharNumber, String panNumber) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
        this.isVerified = false;  // Default to false
    }

    public FlipFitGymOwner() {
        super();
    }

    // Getter and Setter methods...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean isVerified) { this.isVerified = isVerified; }

    // A utility to simulate the collections of gym owners
    public static void main(String[] args) {
        // Create some hardcoded FlipFitGymOwner instances
        FlipFitGymOwner gymOwner1 = new FlipFitGymOwner("owner1@example.com", "password123", 1, "John Doe", "9876543210", "Aadhar1234", "PAN12345");
        FlipFitGymOwner gymOwner2 = new FlipFitGymOwner("owner2@example.com", "password456", 2, "Jane Smith", "9876543211", "Aadhar5678", "PAN67890");
        FlipFitGymOwner gymOwner3 = new FlipFitGymOwner("owner3@example.com", "password789", 1, "Samuel Taylor", "9876543212", "Aadhar91011", "PAN11223");

        // Hardcoded collections (ArrayList, HashSet, HashMap)

        // Using ArrayList to store multiple gym owners
        ArrayList<FlipFitGymOwner> gymOwnersList = new ArrayList<>();
        gymOwnersList.add(gymOwner1);
        gymOwnersList.add(gymOwner2);
        gymOwnersList.add(gymOwner3);

        // Using HashSet to track verified gym owners
        Set<String> verifiedGymOwners = new HashSet<>();
        gymOwner1.setVerified(true);
        verifiedGymOwners.add(gymOwner1.getPanNumber());

        gymOwner2.setVerified(true);
        verifiedGymOwners.add(gymOwner2.getPanNumber());

        // Using HashMap to map gym owners by PAN number
        Map<String, FlipFitGymOwner> gymOwnerMap = new HashMap<>();
        gymOwnerMap.put(gymOwner1.getPanNumber(), gymOwner1);
        gymOwnerMap.put(gymOwner2.getPanNumber(), gymOwner2);
        gymOwnerMap.put(gymOwner3.getPanNumber(), gymOwner3);

        // Display gym owners from ArrayList
        System.out.println("Gym Owners List:");
        for (FlipFitGymOwner owner : gymOwnersList) {
            System.out.println(owner.getName() + " (" + owner.getPanNumber() + ")");
        }

        // Display verified gym owners from HashSet
        System.out.println("\nVerified Gym Owners:");
        for (String pan : verifiedGymOwners) {
            System.out.println("PAN: " + pan);
        }

        // Access a gym owner using HashMap
        System.out.println("\nFetching Gym Owner by PAN (PAN12345):");
        FlipFitGymOwner fetchedOwner = gymOwnerMap.get("PAN12345");
        if (fetchedOwner != null) {
            System.out.println("Gym Owner Found: " + fetchedOwner.getName());
        } else {
            System.out.println("Gym Owner not found.");
        }
    }
}
