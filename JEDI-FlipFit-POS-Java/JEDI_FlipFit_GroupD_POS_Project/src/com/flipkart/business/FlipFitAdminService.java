/**
 * 
 */
package com.flipkart.business;
import java.util.*;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

/**
 * 
 */
public class FlipFitAdminService {

	public List<FlipFitGymOwner> getGymOwners() {
		return new ArrayList<>();
	}
	
	public List<FlipFitGym> getGym() {
		return new ArrayList<>();
	}
	
	public List<FlipFitGymOwner> getPendingGymOwnersRequests() {
		return new ArrayList<>();
	}
	
	public List<FlipFitGym> getPendingGymRequests() {
		return new ArrayList<>();
	}
	
	public boolean approveGymOwnerRequest(FlipFitGymOwner flipFitGymOwner) {
		return false;
	}
	
	public boolean approveGymRequest(FlipFitGym flipFitGym) {
		return false;
	}
}
