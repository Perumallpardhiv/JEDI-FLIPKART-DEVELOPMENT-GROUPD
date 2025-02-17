package com.flipkart.client;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitUser;
import com.flipkart.business.FlipFitAdminBusiness;
import com.flipkart.business.FlipFitGymCustomerBusiness;
import com.flipkart.business.FlipFitGymOwnerBusiness;
import com.flipkart.business.interfaces.IFlipFitAdmin;
import com.flipkart.dao.FlipFitAdminDAOImpl;
import com.flipkart.dao.FlipFitGymCustomerDAOImpl;
import com.flipkart.dao.FlipFitGymOwnerDAOImpl;
import com.flipkart.exceptions.ExceptionHandler;
import com.flipkart.exceptions.InvalidChoiceException;
import com.flipkart.exceptions.WrongCredentialsException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Main class for the Gym FlipFit application, facilitating login, registration, and navigating different roles.
 * The user can log in or register as a Customer, Gym Owner, or Admin, and access respective menus.
 */
public class GymFlipFitApplication {

    /**
     * Main entry point for the Gym FlipFit application.
     * This method presents the user with the main menu options, including login, registration, and exit.
     * Based on user choice, it directs to the appropriate functionality.
     * 
     * @throws InvalidChoiceException if an invalid choice is entered in the menu.
     */
    public static void main(String[] args) throws InvalidChoiceException {
        try {
            Scanner in = new Scanner(System.in);
            int choice = 0;

            // Loop to display the main menu until user selects Exit (choice 5)
            do {
                System.out.println("Welcome To FlipFit: ");
                System.out.println("Type " +
                        "\n 1-> Login, " +
                        "\n 2-> Registration of Customer " +
                        "\n 3-> Registration of Gym Owner " +
                        "\n 4-> Registration of Admin " +
                        "\n 5-> Exit \n"
                );

                choice = in.nextInt();

                // Switch case to handle the different user choices
                try {
                	switch (choice) {
                	case 1: {
                        System.out.println("Login");
                        System.out.print("Enter your emailId:> ");
                        String username = in.next();
                        System.out.print("Enter your password:> ");
                        String password = in.next();
                        System.out.print("Enter your role:> Customer/Admin/GymOwner ");
                        String role = in.next();

                        LocalDate localDate = LocalDate.now();
                        LocalTime localTime = LocalTime.now();
                        System.out.println("Date-->" + localDate);
                        System.out.println("Time-->" + localTime);

                        switch (role) {
                            case "Customer": {
                                FlipFitUser gymCustomer = new FlipFitUser();
                                gymCustomer.setEmailID(username);
                                gymCustomer.setPassword(password);
                                FlipFitGymCustomerDAOImpl flipFitGymCustomerDAO = new FlipFitGymCustomerDAOImpl();
                                FlipFitGymCustomerBusiness GCBservice = new FlipFitGymCustomerBusiness(flipFitGymCustomerDAO);

                                gymCustomer = GCBservice.login(gymCustomer);
                                if (gymCustomer == null) {
                                    throw new WrongCredentialsException();
                                }
                                System.out.println("Customer Menu");
                                GymFlipFitCustomerMenu.getFlipFitCustomerMenu(gymCustomer);
                                break;
                            }
                            case "Admin": {
                                FlipFitAdmin admin = new FlipFitAdmin();
                                admin.setEmailID(username);
                                admin.setPassword(password);
                                FlipFitAdminDAOImpl adminDAO = new FlipFitAdminDAOImpl();
                                IFlipFitAdmin flipFitAdmin = new FlipFitAdminBusiness(adminDAO);
                                if (!flipFitAdmin.adminLogin(admin)) {
                                    throw new WrongCredentialsException();
                                }
                                System.out.println("Admin Menu");
                                GymFlipFitAdminMenu.getAdminView();
                                break;
                            }
                            case "GymOwner": {
                                FlipFitUser gymOwner = new FlipFitUser();
                                gymOwner.setEmailID(username);
                                gymOwner.setPassword(password);
                                FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
                                FlipFitGymOwnerBusiness GOBservice = new FlipFitGymOwnerBusiness(flipFitGymOwnerDAO);

                                gymOwner = GOBservice.login(gymOwner);
                                if (gymOwner == null) {
                                    throw new WrongCredentialsException();
                                }
                                System.out.println("GymOwner Menu");
                                GymFlipFitOwnerMenu.getFlipFitOwnerView(gymOwner);
                                break;
                            }
                        }
                        break;
                    }
                    case 2: {
                        // Registration of a gym customer
                        System.out.println("Registration of gym customer");
                        System.out.print("Enter your email address:> ");
                        String emailID = in.next();
                        System.out.print("Enter your phone number:> ");
                        String phoneNumber = in.next();
                        System.out.print("Enter your city:> ");
                        String city = in.next();
                        System.out.print("Enter your pin code:> ");
                        String pinCode = in.next();
                        System.out.print("Enter your password:> ");
                        String password = in.next();
                        System.out.print("Enter username: ");
                        String username = in.next();

                        // Creating FlipFitUser object and setting properties
                        FlipFitUser gymCustomer = new FlipFitUser();
                        gymCustomer.setEmailID(emailID);
                        gymCustomer.setPassword(password);
                        gymCustomer.setPhoneNumber(phoneNumber);
                        gymCustomer.setUserName(username);

                        // Creating FlipFitGymCustomer object and setting registration details
                        FlipFitGymCustomer flipFitGymCustomer = new FlipFitGymCustomer();
                        flipFitGymCustomer.setEmailID(emailID);
                        flipFitGymCustomer.setPassword(password);
                        flipFitGymCustomer.setPhoneNumber(phoneNumber);
                        flipFitGymCustomer.setUserName(username);
                        flipFitGymCustomer.setCity(city);
                        flipFitGymCustomer.setPinCode(pinCode);
                        flipFitGymCustomer.setRole(1);

                        // Register the customer
                        FlipFitGymCustomerDAOImpl flipFitGymCustomerDAO = new FlipFitGymCustomerDAOImpl();
                        FlipFitGymCustomerBusiness GCBservice = new FlipFitGymCustomerBusiness(flipFitGymCustomerDAO);
                        flipFitGymCustomer = GCBservice.registerCustomer(flipFitGymCustomer);
                        gymCustomer.setUserID(flipFitGymCustomer.getUserId());
                        System.out.println("Registration completed");
                        System.out.println("Customer Menu");

                        // Display customer menu
                        GymFlipFitCustomerMenu.getFlipFitCustomerMenu(gymCustomer);
                        break;
                    }
                    case 3: {
                        // Registration of a gym owner
                        System.out.println("Registration of gym owner");
                        System.out.print("Enter your email address:> ");
                        String emailID = in.next();
                        System.out.print("Enter your phone number:> ");
                        String phoneNumber = in.next();
                        System.out.print("Enter your city:> ");
                        String city = in.next();
                        System.out.print("Enter your pin code:> ");
                        String pinCode = in.next();
                        System.out.print("Enter your password:> ");
                        String password = in.next();
                        System.out.print("Enter username: ");
                        String username = in.next();
                        System.out.print("Enter your panId: ");
                        String panId = in.next();
                        System.out.print("Enter your gstNum: ");
                        String gstNum = in.next();
                        System.out.print("Enter your aadharNumber: ");
                        String aadharNumber = in.next();

                        // Creating FlipFitGymOwner object and setting properties
                        FlipFitGymOwner flipFitOwner = new FlipFitGymOwner();
                        flipFitOwner.setEmailID(emailID);
                        flipFitOwner.setPassword(password);
                        flipFitOwner.setPhoneNumber(phoneNumber);
                        flipFitOwner.setCity(city);
                        flipFitOwner.setPinCode(pinCode);
                        flipFitOwner.setUserName(username);
                        flipFitOwner.setRole(2);
                        flipFitOwner.setGSTIN(gstNum);
                        flipFitOwner.setAadharNumber(aadharNumber);
                        flipFitOwner.setPanId(panId);
                        flipFitOwner.setIsApproved(false);

                        // Register the gym owner
                        FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
                        FlipFitGymOwnerBusiness GOBservice = new FlipFitGymOwnerBusiness(flipFitGymOwnerDAO);
                        GOBservice.registerOwner(flipFitOwner);
                        System.out.println("Registration completed");
                        break;
                    }
                    case 4: {
                        // Registration of admin
                        System.out.println("Registration of Admin");
                        System.out.print("Enter your email address:> ");
                        String emailID = in.next();
                        System.out.print("Enter your phone number:> ");
                        String phoneNumber = in.next();
                        System.out.print("Enter your city:> ");
                        String city = in.next();
                        System.out.print("Enter your pin code:> ");
                        String pinCode = in.next();
                        System.out.print("Enter your password:> ");
                        String password = in.next();
                        System.out.print("Enter username: ");
                        String username = in.next();

                        // Creating FlipFitAdmin object and setting properties
                        FlipFitAdmin flipFitAdmin = new FlipFitAdmin();
                        flipFitAdmin.setEmailID(emailID);
                        flipFitAdmin.setPassword(password);
                        FlipFitAdminDAOImpl flipFitAdminDAO = new FlipFitAdminDAOImpl();
                        FlipFitAdminBusiness GOBservice = new FlipFitAdminBusiness(flipFitAdminDAO);

                        // Register the admin
                        GOBservice.registerAdmin(flipFitAdmin);
                        System.out.println("Registration completed");
                        break;
                    }
                    case 5: {
                        // Exit the application
                        System.out.println("Exit");
                        break;
                    }
                    default: {
                        // Throw exception if an invalid choice is entered
                        throw new InvalidChoiceException("Invalid choice entered: " + choice);
                    }
                }
                } catch (WrongCredentialsException e) {
                    e.printStackTrace();
                }
            } while (choice != 5); // Exit condition
        }
        
        catch (InvalidChoiceException e) {
            // Handle exception for invalid menu choice
            ExceptionHandler.InvalidChoiceMainMenu(e);
        }
    }
}
