package com.flipkart.client;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitGymCentre;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitSlots;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.BookingsBusiness;
import com.flipkart.business.FlipFitGymCentreBusiness;
import com.flipkart.business.FlipFitGymCustomerBusiness;
import com.flipkart.dao.FlipFitBookingDAOImpl;
import com.flipkart.dao.FlipFitGymCentreDAOImpl;
import com.flipkart.dao.FlipFitGymCustomerDAOImpl;
import com.flipkart.exceptions.ExceptionHandler;
import com.flipkart.exceptions.InvalidChoiceException;

import java.util.Scanner;
import java.util.List;

/**
 * Class representing the customer menu in the FlipFit application.
 * This menu allows the customer to view booked slots, view gym centres, 
 * book new slots, and logout.
 */
public class GymFlipFitCustomerMenu {

    /**
     * Displays the customer menu and handles user interactions like 
     * viewing booked slots, viewing gym centres, booking slots, and logging out.
     * 
     * @param gymCustomer The customer for whom the menu is being displayed.
     * @throws InvalidChoiceException If the user enters an invalid choice.
     */
    public static void getFlipFitCustomerMenu(FlipFitUser gymCustomer) throws InvalidChoiceException {
        try {
            // Get the user ID for the logged-in customer
            int userId = gymCustomer.getUserID();
            Scanner sc = new Scanner(System.in);

            // DAO and business service objects
            FlipFitGymCustomerDAOImpl flipFitGymCustomerDAO = new FlipFitGymCustomerDAOImpl();
            FlipFitGymCustomerBusiness FCBservice = new FlipFitGymCustomerBusiness(flipFitGymCustomerDAO);
            FlipFitGymCentreDAOImpl flipFitGymCenterDAO = new FlipFitGymCentreDAOImpl();
            FlipFitGymCentreBusiness FCService = new FlipFitGymCentreBusiness(flipFitGymCenterDAO);
            FlipFitBookingDAOImpl flipFitBookingDAO = new FlipFitBookingDAOImpl();
            BookingsBusiness BService = new BookingsBusiness(flipFitBookingDAO);

            int choice = 0;

            // Main menu loop, exits when the user chooses logout (choice 3)
            do {
                // Display the customer menu options
                System.out.println("FlipFit Customer Menu:> ");
                System.out.println("Choose an option:" +
                        "\n 1. View Booked Slots" +
                        "\n 2. View Centres" +
                        "\n 3. Logout");

                choice = sc.nextInt();

                // Switch case to handle different menu choices
                switch (choice) {
                    case 1: {
                        // Option 1: View booked slots for the customer
                        System.out.println("View Booked Slots:");

                        // Fetch booked slots for the current customer
                        List<FlipFitBooking> bookedSlots = FCBservice.viewBookedSlots(userId);
                        for (FlipFitBooking bookings : bookedSlots) {
                            System.out.println(bookings.userId + " " + bookings.getSlotTime());
                        }

                        // Prompt the customer to cancel a booking if desired
                        System.out.println("Type 2. If you wish to cancel");
                        choice = sc.nextInt();
                        if (choice == 2) {
                            System.out.println("Choose the booking ID you wish to cancel");
                            int bookingId = sc.nextInt();
                            BService.deleteBooking(bookingId); // Cancel the booking
                        }
                        break;
                    }
                    case 2: {
                        // Option 2: View available gym centres
                        System.out.println("View Centres");

                        // Fetch the list of gym centres
                        List<FlipFitGymCentre> centreList = FCBservice.viewCentres();
                        for (FlipFitGymCentre centre : centreList) {
                            System.out.println("CentreId is: " + centre.getCentreID() + "\nCity is: " + centre.getCity() + "\nPincode is: " + centre.getPincode());
                        }

                        // Allow the customer to select a centre for booking a slot
                        System.out.println("Choose a centre you want to book slot in");
                        int centreId = sc.nextInt();

                        // Fetch available slots for the selected centre
                        List<FlipFitSlots> slotsList = FCService.viewAvailableSlots(centreId);
                        System.out.println("These are the available slots:");
                        for (FlipFitSlots flipFitSlots : slotsList) {
                            System.out.println("Slot Id is: " + flipFitSlots.getSlotId() + " Slot Timing is: " + flipFitSlots.getSlotTime() + " Availability is: " + flipFitSlots.getSeatsAvailable() + " CentreId is: " + flipFitSlots.getCentreId());
                        }

                        // Customer chooses the start time for booking a slot
                        System.out.println("Give the startTime you wish to book");
                        int startTime = sc.nextInt();
                        System.out.println("Give the centre ID: ");
                        int centreID = sc.nextInt();

                        // Make the booking
                        BService.makeBooking(userId, centreID, startTime);
                        break;
                    }
                    case 3: {
                        // Option 3: Logout
                        System.out.println("Successful logout");
                        break;
                    }
                    default: {
                        // Handle invalid menu choices by throwing an exception
                        throw new InvalidChoiceException("Invalid choice entered: " + choice);
                    }
                }
            }
            // Continue showing the menu until the user selects to logout (choice 3)
            while (choice != 3);
        } catch (InvalidChoiceException e) {
            // Handle invalid choice exceptions and delegate to a custom exception handler
            ExceptionHandler.InvalidChoiceMainMenu(e);
        }
    }
}
