package com.flipkart.business;

import com.flipkart.bean.*;
import java.util.*;

public class FlipFitCustomerService implements FlipFitCustomerServiceInterface {

    private Map<String, FlipFitCustomer> customers = new HashMap<>();
    private Map<String, List<FlipFitBooking>> bookings = new HashMap<>();
    private Map<String, List<FlipFitGym>> gymsInCities = new HashMap<>();
    private Map<String, List<FlipFitSlot>> slotsInGyms = new HashMap<>();
    private Set<String> bookedSlots = new HashSet<>();
    private Scanner scanner = new Scanner(System.in);

    public FlipFitCustomerService() {
        // Adding customers
        FlipFitCustomer customer1 = new FlipFitCustomer("john@example.com", "pass123", 1, "John Doe", "9876543210", 25, "Bangalore");
        FlipFitCustomer customer2 = new FlipFitCustomer("raj.sharma@gmail.com", "raj456", 2, "Raj Sharma", "9988776655", 30, "Mumbai");

        customers.put(customer1.getEmail(), customer1);
        customers.put(customer2.getEmail(), customer2);

        // Adding gyms in different Indian cities
        FlipFitGym gym1 = new FlipFitGym("G001", "FitHub", "john@example.com", "BLR", "5", "20", true);
        FlipFitGym gym2 = new FlipFitGym("G002", "PowerGym", "owner@example.com", "BLR", "3", "15", false);
        
        FlipFitGym gym3 = new FlipFitGym("G003", "Muscle Factory", "raj.sharma@gmail.com", "MUM", "4", "18", true);
        FlipFitGym gym4 = new FlipFitGym("G004", "Iron Temple", "fitness.pro@gmail.com", "MUM", "6", "25", true);
        
        FlipFitGym gym5 = new FlipFitGym("G005", "Delhi Fitness Hub", "trainer.delhi@yahoo.com", "DEL", "5", "22", true);
        
        FlipFitGym gym6 = new FlipFitGym("G006", "Hyderabad Strength Center", "hyd.fitness@fit.com", "HYD", "4", "16", false);

        gymsInCities.put("BLR", Arrays.asList(gym1, gym2));
        gymsInCities.put("MUM", Arrays.asList(gym3, gym4));
        gymsInCities.put("DEL", Arrays.asList(gym5));
        gymsInCities.put("HYD", Arrays.asList(gym6));

        // Adding slots for Bangalore gyms
        FlipFitSlot slot1 = new FlipFitSlot("S001", "6:00 AM", "7:00 AM", 10, "Trainer A");
        FlipFitSlot slot2 = new FlipFitSlot("S002", "7:00 AM", "8:00 AM", 10, "Trainer B");
        FlipFitSlot slot3 = new FlipFitSlot("S003", "8:00 AM", "9:00 AM", 12, "Trainer C");
        FlipFitSlot slot4 = new FlipFitSlot("S004", "5:00 PM", "6:00 PM", 15, "Trainer D");

        slotsInGyms.put("G001", Arrays.asList(slot1, slot2, slot3, slot4));

        // Adding slots for Mumbai gyms
        FlipFitSlot slot5 = new FlipFitSlot("S005", "6:30 AM", "7:30 AM", 8, "Trainer E");
        FlipFitSlot slot6 = new FlipFitSlot("S006", "7:30 AM", "8:30 AM", 10, "Trainer F");
        FlipFitSlot slot7 = new FlipFitSlot("S007", "6:00 PM", "7:00 PM", 12, "Trainer G");

        slotsInGyms.put("G003", Arrays.asList(slot5, slot6, slot7));

        // Adding slots for Delhi gyms
        FlipFitSlot slot8 = new FlipFitSlot("S008", "7:00 AM", "8:00 AM", 10, "Trainer H");
        FlipFitSlot slot9 = new FlipFitSlot("S009", "6:00 PM", "7:00 PM", 15, "Trainer I");

        slotsInGyms.put("G005", Arrays.asList(slot8, slot9));

        // Adding slots for Hyderabad gyms
        FlipFitSlot slot10 = new FlipFitSlot("S010", "6:00 AM", "7:00 AM", 10, "Trainer J");
        FlipFitSlot slot11 = new FlipFitSlot("S011", "7:00 AM", "8:00 AM", 12, "Trainer K");

        slotsInGyms.put("G006", Arrays.asList(slot10, slot11));
    }


    @Override
    public void getProfile(FlipFitCustomer customer) {
        FlipFitCustomer existingCustomer = customers.get(customer.getEmail());
        if (existingCustomer != null) {
            System.out.println("\nCustomer Profile:");
            System.out.println("Name: " + existingCustomer.getName());
            System.out.println("Email: " + existingCustomer.getEmail());
            System.out.println("Phone Number: " + existingCustomer.getPhoneNumber());
            System.out.println("Age: " + existingCustomer.getAge());
            System.out.println("Address: " + existingCustomer.getAddress());
        } else {
            System.out.println("Customer not found.");
        }
    }

    @Override
    public void editProfile(FlipFitCustomer customer) {
        if (!customers.containsKey(customer.getEmail())) {
            System.out.println("Customer not found.");
            return;
        }

        FlipFitCustomer existingCustomer = customers.get(customer.getEmail());

        System.out.print("Enter new name (current: " + existingCustomer.getName() + "): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) existingCustomer.setName(newName);

        System.out.print("Enter new phone number (current: " + existingCustomer.getPhoneNumber() + "): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty()) existingCustomer.setPhoneNumber(newPhone);

        System.out.print("Enter new age (current: " + existingCustomer.getAge() + "): ");
        String newAge = scanner.nextLine().trim();
        if (!newAge.isEmpty()) existingCustomer.setAge(Integer.parseInt(newAge));

        System.out.print("Enter new address (current: " + existingCustomer.getAddress() + "): ");
        String newAddress = scanner.nextLine().trim();
        if (!newAddress.isEmpty()) existingCustomer.setAddress(newAddress);

        customers.put(customer.getEmail(), existingCustomer);
        System.out.println("Profile updated successfully.");
    }

    @Override
    public List<FlipFitBooking> getBookings(String email) {
        return bookings.getOrDefault(email, new ArrayList<>());
    }

    @Override
    public boolean cancelBooking(String bookingId, String email) {
        List<FlipFitBooking> userBookings = bookings.get(email);
        if (userBookings != null) {
            boolean removed = userBookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
            if (removed) {
                System.out.println("Booking cancelled successfully.");
                return true;
            }
        }
        System.out.println("Booking not found.");
        return false;
    }

    @Override
    public List<FlipFitGym> getGymInCity(String city) {
        return gymsInCities.getOrDefault(city, new ArrayList<>());
    }

    @Override
    public List<FlipFitSlot> getSlotInGym(String gymId) {
        return slotsInGyms.getOrDefault(gymId, new ArrayList<>());
    }

    @Override
    public int bookSlot(String gymId, String slotId, String email, Date date) {
        String bookingKey = slotId + "_" + date.toString();
        if (bookedSlots.contains(bookingKey)) {
            System.out.println("Slot is already fully booked.");
            return -1;
        }

        bookedSlots.add(bookingKey);
        FlipFitBooking newBooking = new FlipFitBooking();
        newBooking.setBookingId(UUID.randomUUID().toString());
        newBooking.setSlotId(slotId);
        newBooking.setGymId(gymId);
        newBooking.setType("Standard");
        newBooking.setDate(date);
        newBooking.setCustomerEmail(email);
        newBooking.setTrainer("Trainer XYZ");

        bookings.computeIfAbsent(email, k -> new ArrayList<>()).add(newBooking);
        System.out.println("Slot booked successfully.");
        return 1;
    }

    @Override
    public boolean isSlotBooked(String slotId, Date date) {
        return bookedSlots.contains(slotId + "_" + date.toString());
    }

    @Override
    public boolean hasBookedSlotAlready(String slotId, String customerEmail, Date date) {
        List<FlipFitBooking> userBookings = bookings.get(customerEmail);
        if (userBookings != null) {
            return userBookings.stream().anyMatch(booking -> booking.getSlotId().equals(slotId) && booking.getDate().equals(date));
        }
        return false;
    }
}
