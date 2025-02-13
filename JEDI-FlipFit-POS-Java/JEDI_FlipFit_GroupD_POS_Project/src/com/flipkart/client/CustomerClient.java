/**
 * 
 */
package com.flipkart.client;
import com.flipkart.business.*;
import com.flipkart.bean.*;
import java.util.*;
/**
 * 
 */
public class CustomerClient {

	/**
	 * @param args
	 */
		// TODO Auto-generated method stub
		FlipFitCustomer flipFitCustomer = new FlipFitCustomer();
		FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
		Scanner sc = new Scanner(System.in);
		
		public void registerCustomer() {
			System.out.println("Enter email: ");
			flipFitCustomer.setName(sc.next());
			System.out.println("Enter password: ");
			flipFitCustomer.setPassword(sc.next());
			System.out.println("Enter Name: ");
			flipFitCustomer.setName(sc.next());
			System.out.println("Enter Phone Number: ");
			flipFitCustomer.setPhoneNumber(sc.next());
			System.out.println("Enter Age: ");
			flipFitCustomer.setAge(Integer.valueOf(sc.next()));
			System.out.println("Enter Address: ");
			flipFitCustomer.setAddress(sc.next());
			System.out.println("FlipFitCustomer registration successful.");
			FlipFitUserService flipFitUserService = new FlipFitUserService();
			flipFitUserService.registerCustomer(flipFitCustomer);
			
		}
		public void viewGyms(String email) {
			getGyms();
			System.out.print("Enter gym ID");
			String gymId = sc.next();
			System.out.print("Enter Date");
			Date date = new Date();
			List<FlipFitSlot> flipFitSlots = flipFitCustomerService.getSlotInGym(gymId);
			for (FlipFitSlot flipFitSlot: flipFitSlots) {
				System.out.print("FlipFitSlot Id: " + flipFitSlot.getSlotId());
				System.out.print("Availability: " + flipFitCustomerService.isSlotBooked(flipFitSlot.getSlotId(), date));
			}
			System.out.print("Enter the slot ID which you want to book: " );
			String slotId = sc.next();
			int bookingResponse = flipFitCustomerService.bookSlot(gymId, slotId, email, date);
			switch(bookingResponse) {
			case 0: 
				System.out.println("You have already booked this time. Cancelling the previous one and booking this slot");
				break;
			case 1:
				System.out.println("FlipFitSlot is already booked");
				break;
			case 2:
				System.out.println("Successfully booked the slot");
				break;
			default:
				System.out.println("FlipFitBooking falied");
			}
		}
		
		public void getGyms() {
			System.out.println("Enter your city: ");
			List<FlipFitGym> flipFitGyms = flipFitCustomerService.getGymInCity(sc.next());
			for (FlipFitGym flipFitGym: flipFitGyms) {
				System.out.print("FlipFitGym Id: " + flipFitGym.getGymId());
				System.out.print("FlipFitGym Owner Email: " + flipFitGym.getOwnerEmail());
				System.out.print("FlipFitGym Name: " + flipFitGym.getGymName());
				System.out.println();
			}
		}
		
		public void cancelBooking(String email) {
			System.out.print("Enter booking ID that you want to cancel");
			String bookingId = sc.next();
			flipFitCustomerService.cancelBooking(bookingId, email);
		}
		
		public void customerMenu(String email) {
			int choice = 0;
			
			while(choice != 4) {				
				System.out.println("Menu:-");
				System.out.println("1.View Gyms \n2.View Booked Slots \n3.Cancel Booked Slots \n4.Exit");
				System.out.print("Enter your choice: ");
				choice = sc.nextInt();
				
				switch (choice) {
					case 1:
						viewGyms(email);
						break;
					case 2:
						flipFitCustomerService.getBookings(email);
						break;
					case 3:
						cancelBooking(email);
						break;
					case 4:
//						applicationClient.mainPage();
						break;
					default:
						System.out.println("Invalid choice!");
				}
			}
		}
	

}
