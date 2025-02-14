/**
 * 
 */
package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.*;

/**
 * 
 */
public class FlipFitGymOwnerService implements FlipFitGymOwnerServiceInterface{
	
	List<FlipFitGymOwner> gymOwners = new ArrayList<>();
	FlipFitGymOwner gymOwner1 = new FlipFitGymOwner("gymowner1@gmail.com", "go1", 2, "Gymowner1", "0000", "0000", "0000");
	FlipFitGymOwner gymOwner2 = new FlipFitGymOwner("gymowner2@gmail.com", "go2", 2, "Gymowner2", "0000", "0000", "0000");
	FlipFitGymOwner gymOwner3 = new FlipFitGymOwner("gymowner3@gmail.com", "go3", 2, "Gymowner3", "0000", "0000", "0000");
	FlipFitGymOwner gymOwner4 = new FlipFitGymOwner("gymowner4@gmail.com", "go4", 2, "Gymowner4", "0000", "0000", "0000");

	List<FlipFitGym> gyms = new ArrayList<>();
	FlipFitGym gym1 = new FlipFitGym("G001", "FitHub", "john@example.com", "BLR", "5", "20", true);
	FlipFitGym gym2 = new FlipFitGym("G002", "PowerGym", "owner@example.com", "BLR", "3", "15", false);
	FlipFitGym gym3 = new FlipFitGym("G003", "Muscle Factory", "raj.sharma@gmail.com", "MUM", "4", "18", true);
	FlipFitGym gym4 = new FlipFitGym("G004", "Iron Temple", "fitness.pro@gmail.com", "MUM", "6", "25", true);
	FlipFitGym gym5 = new FlipFitGym("G005", "Delhi Fitness Hub", "trainer.delhi@yahoo.com", "DEL", "5", "22", true);
	FlipFitGym gym6 = new FlipFitGym("G006", "Hyderabad Strength Center", "hyd.fitness@fit.com", "HYD", "4", "16", false);

	public FlipFitGymOwnerService() {
		gymOwners.add(gymOwner1);
		gymOwners.add(gymOwner2);
		gymOwners.add(gymOwner3);
		gymOwners.add(gymOwner4);
		
		gyms.add(gym1);
		gyms.add(gym2);
		gyms.add(gym3);
		gyms.add(gym4);
		gyms.add(gym5);
		gyms.add(gym6);
	}

	public FlipFitGymOwner getProfile(String email) {
		for(FlipFitGymOwner gymOwner: gymOwners) {
			if(gymOwner.getEmail().equals(email)) return gymOwner;
		}
		return null;
	}

	public void editProfile(FlipFitGymOwner flipFitGymOwner, String email) {
		for(int i=0; i<gymOwners.size(); i++) {
			FlipFitGymOwner prevGymOwner = gymOwners.get(i);
			if(prevGymOwner.getEmail().equals(email)) {
				prevGymOwner.setName(flipFitGymOwner.getName());
				prevGymOwner.setPhoneNumber(flipFitGymOwner.getPhoneNumber());
				prevGymOwner.setPanNumber(flipFitGymOwner.getPanNumber());
				prevGymOwner.setAadharNumber(flipFitGymOwner.getAadharNumber());
				gymOwners.add(i, prevGymOwner);
				System.out.println("Successfully edited your profile");
				break;
			}
		}
		System.out.println("We could not find your profile, please retry!");
	}

	public boolean addGym(FlipFitGym flipFitGym) {
		gyms.add(flipFitGym);
		System.out.println("Added Gym Successfully!");
		return true;
	}

	public void editGym(FlipFitGym flipFitGym) {
		for(int i=0; i<gyms.size(); i++) {
			FlipFitGym prevGym = gyms.get(i);
			if(prevGym.getGymId().equals(flipFitGym.getGymId())) {
				prevGym.setGymName(flipFitGym.getGymName());
				prevGym.setAddress(flipFitGym.getAddress());
				prevGym.setSlotCount(flipFitGym.getSlotCount());
				prevGym.setSeatsPerSlotCount(flipFitGym.getSeatsPerSlotCount());
				gyms.add(i, prevGym);
				System.out.println("Edited Gym Details Successfully");
				break;
			}
		}
		System.out.println("We could not find the gym with the ID provided, please retry!");
	}
	
	public List<FlipFitGym> getGymDetail(String gymOwnerEmail) {
		List<FlipFitGym> ownersGyms = new ArrayList<>();
		for(FlipFitGym gym: gyms) {
			if(gym.getOwnerEmail().equals(gymOwnerEmail)) { 
				ownersGyms.add(gym);
			}
		}
		return ownersGyms;
	}
	
	public boolean isApproved(String email) {
		for(FlipFitGymOwner gymOwner: gymOwners) {
			if(gymOwner.getEmail().equals(email)) return gymOwner.isVerified();
		}
		return false;
	}
	
	public boolean isGymApproved(String gymId) {
		for(FlipFitGym gym: gyms) {
			if(gym.getGymId().equals(gymId)) gym.isVerified();
		}
		return false;
	}
}
