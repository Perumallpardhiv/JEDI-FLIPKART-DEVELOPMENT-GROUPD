package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.business.FlipFitAdminBusiness;
import com.flipkart.business.FlipFitGymOwnerBusiness;
import com.flipkart.business.interfaces.IFlipFitAdmin;
import com.flipkart.dao.FlipFitAdminDAOImpl;
import com.flipkart.exceptions.ExceptionHandler;
import com.flipkart.exceptions.InvalidChoiceException;

import java.util.List;
import java.util.Scanner;

/**
 * Class representing the admin menu for managing various operations related to gym owners, customers, and centers.
 * The class facilitates interaction with the admin to view pending requests, approved owners, customers, and centers.
 */
public class GymFlipFitAdminMenu {

    /**
     * Displays the admin menu and handles the admin's selection.
     * This method handles the different options available to the admin, such as:
     * - Viewing pending owner requests
     * - Viewing approved owners
     * - Viewing all customers
     * - Viewing all centers using an owner's ID
     * 
     * @throws InvalidChoiceException if an invalid menu option is selected
     */
    public static void getAdminView() throws InvalidChoiceException {
        try {
            Scanner sc = new Scanner(System.in);
            FlipFitAdminDAOImpl adminUser = new FlipFitAdminDAOImpl();
            FlipFitAdminBusiness adminService = new FlipFitAdminBusiness(adminUser);

            // Displaying menu options
            System.out.println("1. View Pending Requests");
            System.out.println("2. View Approved Owners");
            System.out.println("3. View all FlipFit Customers");
            System.out.println("4. View all Centres Using OwnerId");

            // Getting the admin's choice
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    // Fetch and display list of pending gym owners
                    List<FlipFitGymOwner> flipFitGymOwnerList = adminService.getPendingOwnerList();
                    for (FlipFitGymOwner flipFitGymOwner : flipFitGymOwnerList) {
                        System.out.println("Owner ID :" + flipFitGymOwner.getUserId() + " Aadhar :" + flipFitGymOwner.getAadharNumber());
                    }

                    // Allow admin to approve an owner
                    System.out.println("Type the ownerId of owner you wish to approve");
                    int ownerId = sc.nextInt();
                    boolean isApproved = adminService.validateOwner(ownerId); // Validate and approve owner
                    break;
                }
                case 2: {
                    // Display list of approved owners
                    System.out.println("Printing list of Approved Owners");
                    List<FlipFitGymOwner> flipFitGymOwnerList = adminService.getApprovedOwnerList();
                    for (FlipFitGymOwner flipFitGymOwner : flipFitGymOwnerList) {
                        System.out.println("Owner ID :" + flipFitGymOwner.getUserId() + " Aadhar :" + flipFitGymOwner.getAadharNumber());
                    }
                    break;
                }
                case 3: {
                    // Display list of all FlipFit customers
                    List<FlipFitGymCustomer> customersList = adminService.getUserList();
                    for (FlipFitGymCustomer customers : customersList) {
                        System.out.println("CustomerID: " + customers.getUserId() + " CustomerName :" + customers.getUserName());
                    }
                    break;
                }
                case 4: {
                    // Display centers for a particular owner
                    System.out.println("Type the ownerId of owner for which you wish to view Centres");
                    Scanner in = new Scanner(System.in);
                    int ownerId = in.nextInt();
                    List<FlipFitGymCentre> flipFitGymCentres = adminService.getGymCentreUsingOwnerId(ownerId);

                    // Check if any centers exist for the given owner ID
                    if (flipFitGymCentres.isEmpty()) {
                        System.out.println("No centres found for owner ID " + ownerId);
                    } else {
                        System.out.println("Printing All Centres of Owner");
                        for (FlipFitGymCentre gymCentre : flipFitGymCentres) {
                            System.out.println("CentreID: " + gymCentre.getCentreID() + " City :" + gymCentre.getCity() + " Capacity: " + gymCentre.getCapacity());
                        }
                    }
                    break;
                }
                default: {
                    // Handle invalid choice
                    throw new InvalidChoiceException("Invalid choice entered: " + choice);
                }
            }
        } catch (InvalidChoiceException e) {
            // Handle exception for invalid menu choice
            ExceptionHandler.InvalidChoiceMainMenu(e);
        }
    }
}
