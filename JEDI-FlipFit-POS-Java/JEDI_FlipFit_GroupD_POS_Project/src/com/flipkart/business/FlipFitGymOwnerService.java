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
public class FlipFitGymOwnerService {

	public FlipFitGymOwner getProfile(String Id) {
		return new FlipFitGymOwner();
	}

	public void editProfile(FlipFitGymOwner flipFitGymOwner) {

	}
	
	public boolean addGym(FlipFitGym flipFitGym) {
		return false;
	}
	
	public void editGym(FlipFitGym flipFitGym) {
		
	}
	
	public List<FlipFitGym> getGymDetail(String gymOwnerEmail) {
		return new ArrayList<>();
	}
	
	public boolean isApproved(String email) {
		return false;
	}
	
	public boolean isGymApproved(FlipFitGym flipFitGym) {
		return false;
	}
}
