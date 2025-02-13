/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.FlipFitGymOwnerService;
import com.flipkart.business.FlipFitUserService;

/**
 * 
 */
public class GymOwnerClient {
	
	FlipFitGymOwner flipFitGymOwner = new FlipFitGymOwner();
	FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerService();

	/**
	 * @param args
	 */
	
	public void gymOwnerRegistration(Scanner in) {
		System.out.println("Enter FlipFitGymOwner Details : ");
		System.out.println("Enter Name : ");
		flipFitGymOwner.setName(in.next());
		System.out.println("Enter Phone Number : ");
		flipFitGymOwner.setPhoneNumber(in.next());
		System.out.println("Enter PAN : ");
		flipFitGymOwner.setPanNumber(in.next());
		System.out.println("Enter Aadhaar : ");
		flipFitGymOwner.setAadharNumber(in.next());
		System.out.println("Enter Email : ");
		flipFitGymOwner.setEmail(in.next());
		System.out.println("Enter Password : ");
		flipFitGymOwner.setPassword(in.next());
		flipFitGymOwner.setRoleId(2);
		System.out.println("FlipFitGym registration successful.");
		
		FlipFitUserService flipFitUserService = new FlipFitUserService();
		flipFitUserService.registerGymOwner(flipFitGymOwner);
		
		
	}
	
	public void editProfile(Scanner in) {
		System.out.println("Enter Details: ");
		System.out.println("Enter Name : ");
		flipFitGymOwner.setName(in.next());
		System.out.println("Enter Phone Number : ");
		flipFitGymOwner.setPhoneNumber(in.next());
		System.out.println("Enter PAN : ");
		flipFitGymOwner.setPanNumber(in.next());
		System.out.println("Enter Aadhaar : ");
		flipFitGymOwner.setAadharNumber(in.next());
		
		flipFitGymOwnerService.editProfile(flipFitGymOwner);
	}

	public void viewProfile(Scanner in) {
		System.out.println("Enter Id: ");
		flipFitGymOwner = flipFitGymOwnerService.getProfile(in.next());
		System.out.println("Name : " + flipFitGymOwner.getName());
		System.out.println("Phone Number : " + flipFitGymOwner.getPhoneNumber());
		System.out.println("PAN : " + flipFitGymOwner.getPanNumber());
		System.out.println("Aadhaar : " + flipFitGymOwner.getAadharNumber());
		
	}

	public void addGym(Scanner in) {
		System.out.println("Enter FlipFitGym Details: ");
		
		FlipFitGym flipFitGym = new FlipFitGym();
		System.out.println("Enter GymName : ");
		flipFitGym.setGymName(in.next());
		System.out.println("Enter Owner Email : ");
		flipFitGym.setOwnerEmail(in.next());
		System.out.println("Enter Address : ");
		flipFitGym.setAddress(in.next());
		System.out.println("Enter SlotCount : ");
		flipFitGym.setSlotCount(in.next());
		System.out.println("Enter seatsPerSlotCount : ");
		flipFitGym.setSeatsPerSlotCount(in.next());
		flipFitGym.setVerified(false);
		
		flipFitGymOwnerService.addGym(flipFitGym);
		

	}

	public void editGym(Scanner in) {
		System.out.println("Enter FlipFitGym Details: ");
		
		FlipFitGym flipFitGym = new FlipFitGym();
		System.out.println("Enter GymName : ");
		flipFitGym.setGymName(in.next());
		System.out.println("Enter Owner Email : ");
		flipFitGym.setOwnerEmail(in.next());
		System.out.println("Enter Address : ");
		flipFitGym.setAddress(in.next());
		System.out.println("Enter SlotCount : ");
		flipFitGym.setSlotCount(in.next());
		System.out.println("Enter seatsPerSlotCount : ");
		flipFitGym.setSeatsPerSlotCount(in.next());
		flipFitGym.setVerified(false);
		
		flipFitGymOwnerService.editGym(flipFitGym);
	}

	public void getGymDetails(Scanner in) {
		flipFitGymOwnerService.getGymDetail(flipFitGymOwner.getEmail());
	}

	public void gymOwnerMenu(Scanner in) {
		System.out.println("Welcome! Here are the actions you can perform!");
		
		System.out.println("1. View Profile");
		System.out.println("2. Edit Profile");
		System.out.println("3. Add FlipFitGym");
		System.out.println("4. Edit FlipFitGym");
		System.out.println("5. View All FlipFitGym Details");
		
		int choice = in.nextInt();
		
		switch(choice) {
		case 1: viewProfile(in);
		case 2: editProfile(in);
		case 3: addGym(in);
		case 4: editGym(in);
		case 5: getGymDetails(in);
		}
		
	}

}
