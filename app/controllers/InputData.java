package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 * @file InputData.java
 * @brief Controller for data input of properties
 * @author michaelfoy
 * @version 2016-06-16
 */
public class InputData extends Controller {

  /**
   * Loads the input data page if a user is logged in
   */
  public static void index() {
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
   * Registers residence data in the database
   * 
   * @param id Id of the user registering the residence
   * @param location The coordinates of the property location
   * @param eircode Eircode of the property
   * @param rent The rental rate
   * @param bedrooms The number of bedrooms
   * @param numberBathrooms The number of bathrooms
   * @param area The area of the property in metres squared
   * @param residenceType The type of property it is
   */
  public static void inputData(Long id, String location, String eircode, String rent,
      String bedrooms, String numberBathrooms, String area, String residenceType) {
    Logger.info("New residence registered at: " + location);
    int rentInt = Integer.parseInt(rent);
    int bedroomsInt = Integer.parseInt(bedrooms);
    int bathroomsInt = Integer.parseInt(numberBathrooms);
    int areaInt = Integer.parseInt(area);
    
    Residence residence = new Residence(location, eircode, rentInt, bedroomsInt, bathroomsInt, areaInt, residenceType);
    residence.landlord = Landlord.findById(id);
    residence.editDate();
    residence.save();
    LandlordConfig.index();
  }
}
