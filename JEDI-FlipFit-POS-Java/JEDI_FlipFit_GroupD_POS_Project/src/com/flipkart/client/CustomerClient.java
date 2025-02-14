package com.flipkart.client;

import com.flipkart.business.*;
import com.flipkart.bean.*;
import java.util.*;

public class CustomerClient {

    private FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
    private Scanner sc = new Scanner(System.in);

    public void registerCustomer() {
        FlipFitCustomer flipFitCustomer = new FlipFitCustomer();

        System.out.print("Enter Email: ");
        flipFitCustomer.setEmail(sc.next());

        System.out.print("Enter Password: ");
        flipFitCustomer.setPassword(sc.next());

        sc.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        flipFitCustomer.setName(sc.nextLine());

        System.out.print("Enter Phone Number: ");
        flipFitCustomer.setPhoneNumber(sc.next());

        System.out.print("Enter Age: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid age.");
            sc.next(); // discard invalid input
        }
        flipFitCustomer.setAge(sc.nextInt());

        sc.nextLine(); // Consume newline

        System.out.print("Enter Address: ");
        flipFitCustomer.setAddress(sc.nextLine());

        System.out.println("\n✅ FlipFitCustomer registration successful!\n");

        FlipFitUserService flipFitUserService = new FlipFitUserService();
        flipFitUserService.registerCustomer(flipFitCustomer);
    }

    public void viewGyms(String email) {
        getGyms();

        System.out.print("\nEnter Gym ID: ");
        String gymId = sc.next();

        Date date = new Date();
        List<FlipFitSlot> flipFitSlots = flipFitCustomerService.getSlotInGym(gymId);

        if (flipFitSlots.isEmpty()) {
            System.out.println("No available slots for this gym.");
            return;
        }

        System.out.println("\nAvailable Slots:");
        for (FlipFitSlot flipFitSlot : flipFitSlots) {
            System.out.println("Slot ID: " + flipFitSlot.getSlotId());
            System.out.println("Time: " + flipFitSlot.getStartTime() + " - " + flipFitSlot.getEndTime());
            System.out.println("Trainer: " + flipFitSlot.getTrainer());
            System.out.println("Available: " + (flipFitCustomerService.isSlotBooked(flipFitSlot.getSlotId(), date) ? "No" : "Yes"));
            System.out.println("-------------------------");
        }

        System.out.print("\nEnter the Slot ID you want to book: ");
        String slotId = sc.next();

        int bookingResponse = flipFitCustomerService.bookSlot(gymId, slotId, email, date);

        switch (bookingResponse) {
            case -1:
                System.out.println("⚠ Slot is already booked!");
                break;
            case 1:
                System.out.println("✅ Successfully booked the slot!");
                break;
            default:
                System.out.println("❌ Booking failed!");
        }
    }

    public void getGyms() {
        System.out.print("\nEnter your city: ");
        String city = sc.next();
        List<FlipFitGym> flipFitGyms = flipFitCustomerService.getGymInCity(city);

        if (flipFitGyms.isEmpty()) {
            System.out.println("No gyms found in this city.");
            return;
        }

        System.out.println("\nAvailable Gyms:");
        for (FlipFitGym flipFitGym : flipFitGyms) {
            System.out.println("Gym ID: " + flipFitGym.getGymId());
            System.out.println("Owner Email: " + flipFitGym.getOwnerEmail());
            System.out.println("Gym Name: " + flipFitGym.getGymName());
            System.out.println("Address: " + flipFitGym.getAddress());
            System.out.println("-------------------------");
        }
    }

    public void cancelBooking(String email) {
        System.out.print("\nEnter Booking ID to cancel: ");
        String bookingId = sc.next();

        boolean cancelled = flipFitCustomerService.cancelBooking(bookingId, email);

        if (cancelled) {
            System.out.println("✅ Booking cancelled successfully.");
        } else {
            System.out.println("❌ Booking not found or cancellation failed.");
        }
    }

    public void viewBookedSlots(String email) {
        List<FlipFitBooking> bookings = flipFitCustomerService.getBookings(email);

        if (bookings.isEmpty()) {
            System.out.println("No booked slots found.");
            return;
        }

        System.out.println("\nYour Booked Slots:");
        for (FlipFitBooking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Gym ID: " + booking.getGymId());
            System.out.println("Slot ID: " + booking.getSlotId());
            System.out.println("Date: " + booking.getDate());
            System.out.println("Trainer: " + booking.getTrainer());
            System.out.println("-------------------------");
        }
    }

    public void customerMenu(String email) {
        int choice = -1;

        while (choice != 4) {
            System.out.println("\n======= FlipFit Menu =======");
            System.out.println("1. View Gyms");
            System.out.println("2. View Booked Slots");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1-4.");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewGyms(email);
                    break;
                case 2:
                    viewBookedSlots(email);
                    break;
                case 3:
                    cancelBooking(email);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }

 
}
