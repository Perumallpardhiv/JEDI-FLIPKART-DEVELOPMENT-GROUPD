package com.flipkart.DAO;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;


public interface FlipFitUserDAO {
	public boolean authenticateUser(FlipFitUser user);

	public boolean registerCustomer(FlipFitCustomer customer);

	public boolean registerGymOwner(FlipFitGymOwner gymOwner);
}


