package com.flipkart.client;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.*;

import java.util.*;

/**
 * 
 */

public class AdminClient {

	FlipFitAdminService flipFitAdminService = new FlipFitAdminService();

	List<FlipFitGym> pendingGymList = flipFitAdminService.getPendingGymRequests();
	List<FlipFitGymOwner> pendingGymOwnerList = flipFitAdminService.getPendingGymOwnersRequests();
	List<FlipFitGymOwner> gymOwnerList = flipFitAdminService.getGymOwners();
	List<FlipFitGym> gymList = flipFitAdminService.getGym();

	

	public void approvePendingGymOwnerRequest(List<FlipFitGymOwner> pendingGymOwnerList) {
		for (FlipFitGymOwner flipFitGymOwner : pendingGymOwnerList) {
			System.out.println("Approved gym owner" + flipFitGymOwner.getName());
			flipFitAdminService.approveGymOwnerRequest(flipFitGymOwner);
		}
	}

	public void approvePendingGymRequest() {
		for (FlipFitGym flipFitGym : pendingGymList) {
			System.out.println("Approved gym" + flipFitGym.getGymName());
			flipFitGym.setVerified(true);
			flipFitAdminService.approveGymRequest(flipFitGym);
		}
	}

	public void AdminPage(Scanner in) throws Exception {
		while (true) {
//			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. View All FlipFitGym ");
			System.out.println("2. View All FlipFitGym Owners");
			System.out.println("3.  Approve Pending FlipFitGym  Requests");
			System.out.println("4. 	Approve pending FlipFitGym owner request");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			int choice = in.nextInt();
			switch (choice) {
			// Case statements
			case 1:
				viewAllGymOwners();
				break;
			case 2:
				viewAllGyms();
				break;
			case 3:
				approvePendingGymRequest();
				break;
			case 4:
//				approvePendingGymOwnerRequest();
				break;

			// Default case statement
			default:
				System.out.println("Wrong choice");
			}
		}
}
			
	public void viewAllGyms() {
		for (FlipFitGym flipFitGym : gymList) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("FlipFitGym Id-->" + flipFitGym.getGymId());
			System.out.println("FlipFitGym Name-->" + flipFitGym.getGymName());
			System.out.println("FlipFitGym Owner Mail-->" + flipFitGym.getOwnerEmail());
			System.out.println("FlipFitGym Address-->" + flipFitGym.getAddress());
			System.out.println("FlipFitGym FlipFitSlot Count-->" + flipFitGym.getSlotCount());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		}
	}

	public void viewAllGymOwners() {
		for (FlipFitGymOwner flipFitGymOwner : gymOwnerList) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("FlipFitGym Owner Name-->" + flipFitGymOwner.getName());
			System.out.println("FlipFitGym Owner phone numver-->" + flipFitGymOwner.getPhoneNumber());
			System.out.println("FlipFitGym Owner Aadhar-->" + flipFitGymOwner.getAadharNumber());
			System.out.println("FlipFitGym Owner panNumber-->" + flipFitGymOwner.getPanNumber());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		}

	}
}
