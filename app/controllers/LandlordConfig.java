package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file LandlordConfig.java
 * @brief Controller for pages for landlord profile configuration
 * @author michaelfoy
 * @version 2016-27-07
 */
public class LandlordConfig extends Controller {
  
  /**
   * If Landlord logged in, loads configuration page
   */
  public static void index() {
    String userId = session.get("logged_in_landlord");
    if (userId != null) {
      Landlord user = Landlords.getCurrentLandlord();
      List<Residence> properties = user.properties;
      render(user, properties);
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * If Landlord logged in, loads edit profile page
   */
  public static void editProfile() {
    String userId = session.get("logged_in_landlord");
    if (userId != null) {
      Landlord user = Landlords.getCurrentLandlord();
      render(user);
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * Loads edit Residence page
   */
  public static void editResidence(Long residenceId) {
    Residence residence = Residence.findById(residenceId);
    render(residence);
  }
  
  /**
   * Allows landlord to edit name and address information
   * 
   * @param firstName Landlord's changed first name
   * @param lastName Landlord's changed first name
   * @param address User's changed address
   */
  public static void profileInfo(String firstName, String lastName, String address) {
    Landlord user = Landlords.getCurrentLandlord();    
    if (!firstName.isEmpty()) { user.firstName = firstName; }
    if (!lastName.isEmpty()) { user.lastName = lastName; }
    if (!address.isEmpty()) { user.address = address; }
    user.save();
    index();
  }
  
  /**
   * Allows rent of a residence to be modified
   * 
   * @param rent Newly adjusted rent
   * @param id Id of the residence
   */
  public static void residenceEdit(String rent, Long id) {
      Residence residence = Residence.findById(id);
      int num = Integer.parseInt(rent);
      residence.rent = num;
      Logger.info("Rent of " + residence.eircode + " changed to €" + rent);
      residence.save();
      index();
    }
  
  /**
   * Routes the form data to either edit or delete residence object
   * 
   * @param residenceId Id of the residence
   * @param buttonChoice String 'edit' or 'delete'
   */
  public static void residenceData(Long residenceId, String buttonChoice) {
    if (buttonChoice.equals("edit")) {
      editResidence(residenceId);
    } else if (buttonChoice.equals("delete")) {
      Residence residence = Residence.findById(residenceId);
      Logger.info("Deleting residence: " + residence.eircode);
      residence.delete();
      index();
    } else {
      Logger.info("error");
    }
  }
}
