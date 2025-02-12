/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class Gym {
	private String gymId;
	private String gymName;
	private String ownerEmail;
	private String address;
<<<<<<< HEAD
	private String slotCount;
	private String seatsPerSlotCount;
	private boolean isVerified;
=======
	private int slotCount;
	private int seatsPerSlotCount;
	private boolean isVerified;
	
	public Gym() {
		
	}
	
	public Gym(String gymId, String gymName, String ownerEmail, String address, int slotCount, int seatsPerSlotCount, boolean isVerified) {
		this.gymId = gymId;
		this.gymName = gymName;
		this.ownerEmail = ownerEmail;
		this.address = address;
		this.slotCount = slotCount;
		this.seatsPerSlotCount = seatsPerSlotCount;
		this.isVerified = isVerified;
	}
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893

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

<<<<<<< HEAD
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
=======
	public int getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(int slotCount) {
		this.slotCount = slotCount;
	}

	public int getSeatsPerSlotCount() {
		return seatsPerSlotCount;
	}

	public void setSeatsPerSlotCount(int seatsPerSlotCount) {
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
		this.seatsPerSlotCount = seatsPerSlotCount;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
<<<<<<< HEAD

}
=======
	
	public String toString() {
		
		String s = "Gym Id : " + this.gymId + 
				"\nGym Name : " + this.gymName + 
				"\nGym Owner Email : " + this.getOwnerEmail() + 
				"\nGym Address : " + this.getAddress() + 
				"\nGym Slotcount : " + this.getSlotCount() + 
				"\nSeat per slot count : " + this.getSeatsPerSlotCount() + 
				"\nVerification : " + (this.isVerified() ? "Yes" : "No");	
		return s;
		
	}

}
>>>>>>> 61684daef6c97ae4cfd2bd25c5f5d3db96a1d893
