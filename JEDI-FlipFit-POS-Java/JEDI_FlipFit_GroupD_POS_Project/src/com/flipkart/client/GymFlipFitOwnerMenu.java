package com.flipkart.client;

import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.FlipFitGymOwnerBusiness;
import com.flipkart.dao.FlipFitGymOwnerDAOImpl;
import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.bean.FlipFitSlots;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing the gym owner menu in the FlipFit application.
 * This menu allows gym owners to manage their gym centres, including adding new centres,
 * viewing their existing centres, and adding slots to those centres.
 */
public class GymFlipFitOwnerMenu {

    /**
     * Displays the gym owner menu and handles interactions such as adding new centres,
     * viewing existing centres, and adding slots to centres.
     *
     * @param gymOwner The gym owner for whom the menu is being displayed.
     * @throws InvalidChoiceException If the user enters an invalid choice.
     */
    public static void getFlipFitOwnerView(FlipFitUser gymOwner) throws InvalidChoiceException {
        try {
            // DAO and business service objects for gym owner operations
            FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
            FlipFitGymOwnerBusiness GOBservice = new FlipFitGymOwnerBusiness(flipFitGymOwnerDAO);
            Scanner sc = new Scanner(System.in);
            int choice = 0;

            // Main menu loop, continues until the user logs out (choice 3)
            do {
                // Display the owner menu options
                System.out.println("Gym Owner Menu:> ");
                System.out.println("Choose an option:" +
                        "\n 1. Add Centre" +
                        "\n 2. View Centres" +
                        "\n 3. Logout");

                choice = sc.nextInt();

                // Switch case to handle different menu choices
                switch (choice) {
                    case 1: {
                        // Option 1: Add a new gym centre
                        System.out.println("Give details to add Centre : ");
                        Scanner scanner = new Scanner(System.in);

                        // Fetch the owner ID for the gym owner
                        int ownerID = gymOwner.getUserID();

                        // Input details for the new gym centre
                        System.out.println("Enter Capacity: ");
                        int capacity = scanner.nextInt();
                        System.out.println("Enter City: ");
                        String city = scanner.next();
                        System.out.println("Enter State: ");
                        String state = scanner.next();
                        System.out.println("Enter Pincode: ");
                        String pincode = scanner.next();

                        // Create a new FlipFitGymCentre object and set its properties
                        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
                        flipFitGymCentre.setOwnerID(ownerID);
                        flipFitGymCentre.setCapacity(capacity);
                        flipFitGymCentre.setCity(city);
                        flipFitGymCentre.setState(state);
                        flipFitGymCentre.setPincode(pincode);
                        flipFitGymCentre.setApproved(true);

                        // Add the new centre using the business service
                        GOBservice.addCentre(flipFitGymCentre);

                        // Inform the user that the centre has been successfully created
                        System.out.println("Gym Centre created successfully.");
                        break;
                    }
                    case 2: {
                        // Option 2: View gym centres for the current owner
                        System.out.println("View Centres for the owner : " + gymOwner.getUserID());
                        FlipFitGymOwner flipFitGymOwner = new FlipFitGymOwner();
                        flipFitGymOwner.setUserId(gymOwner.getUserID());

                        // Fetch and display the list of centres owned by the gym owner
                        List<FlipFitGymCentre> centreList = GOBservice.viewCentres(flipFitGymOwner);
                        for (FlipFitGymCentre centre : centreList) {
                            System.out.println("CentreID : " + centre.getCentreID() + " Capacity : " + centre.getCapacity() + " City : " + centre.getCity() + " State : " + centre.getState());
                        }

                        int centreChoice = 0;

                        // Loop for managing centre-specific actions, such as adding slots
                        do {
                            System.out.println("Choose an option:" +
                                    "\n 1. Add Slots" +
                                    "\n 2. Exit");
                            centreChoice = sc.nextInt();

                            switch (centreChoice) {
                                case 1: {
                                    // Option 1: Add slots to an existing gym centre
                                    System.out.println("Select a Centre to add slots (Enter CentreID): ");
                                    int selectedCentreID = sc.nextInt();

                                    // Input the number of slots to add
                                    System.out.println("Enter Number of Slots to add: ");
                                    int numberOfSlots = sc.nextInt();

                                    // Add the specified number of slots to the selected centre
                                    for (int i = 0; i < numberOfSlots; i++) {
                                        System.out.println("Enter Slot Time for Slot " + (i + 1) + " : ");
                                        int slotTime = sc.nextInt();

                                        System.out.println("Enter total number of seats: ");
                                        int seats = sc.nextInt();

                                        // Create and add the new slot
                                        FlipFitSlots flipFitSlot = new FlipFitSlots(selectedCentreID, slotTime, seats);
                                        GOBservice.addSlot(flipFitSlot);
                                        System.out.println("Slot " + (i + 1) + " added successfully.");
                                    }
                                    break;
                                }
                                case 2: {
                                    // Option 2: Exit the centre-specific menu
                                    System.out.println("Exiting to previous menu...");
                                    break;
                                }
                                default: {
                                    // Handle invalid choice for centre menu
                                    throw new InvalidChoiceException("Invalid choice entered: " + centreChoice);
                                }
                            }
                        } while (centreChoice != 2); // Loop until user selects to exit
                        break;
                    }
                    case 3: {
                        // Option 3: Logout
                        System.out.println("Successful logout");
                        break;
                    }
                    default: {
                        // Handle invalid main menu choices
                        throw new InvalidChoiceException("Invalid choice entered: " + choice);
                    }
                }
            } while (choice != 3); // Loop until user logs out
        } catch (InvalidChoiceException e) {
            // Catch and display any invalid choice exceptions
            System.out.println(e.getMessage());
        }
    }
}
