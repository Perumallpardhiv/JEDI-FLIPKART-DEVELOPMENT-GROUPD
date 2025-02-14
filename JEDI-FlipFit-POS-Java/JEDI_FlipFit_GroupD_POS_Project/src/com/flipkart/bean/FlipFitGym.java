package com.flipkart.bean;

public class FlipFitGym {
	private String gymId;
	private String gymName;
	private String ownerEmail;
	private String address;
	private String slotCount;
	private String seatsPerSlotCount;
	private boolean isVerified;
	
	public FlipFitGym() {
		
	}

	// Constructor
	public FlipFitGym(String gymId, String gymName, String ownerEmail, String address, String slotCount, String seatsPerSlotCount, boolean isVerified) {
		this.gymId = gymId;
		this.gymName = gymName;
		this.ownerEmail = ownerEmail;
		this.address = address;
		this.slotCount = slotCount;
		this.seatsPerSlotCount = seatsPerSlotCount;
		this.isVerified = isVerified;
	}

	// Getters and Setters
	public String getGymId() {
		return gymId;
	}

	public void setGymId(String gymId) {
		this.gymId = gymId;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(String slotCount) {
		this.slotCount = slotCount;
	}

	public String getSeatsPerSlotCount() {
		return seatsPerSlotCount;
	}

	public void setSeatsPerSlotCount(String seatsPerSlotCount) {
		this.seatsPerSlotCount = seatsPerSlotCount;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
}