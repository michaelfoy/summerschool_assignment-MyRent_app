package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file Landlords.java
 * @brief Controller for pages for landlords
 * @author michaelfoy
 * @version 2016-27-07
 */
public class Landlords extends Controller {

  /**
   * Loads sign up page
   */
  public static void signup() {
    render();
  }

  /**
   * Loads log in page
   */
  public static void login() {
    render();
  }

  /**
   * Method to register a user
   * 
   * @param firstName landlord's first name
   * @param lastName landlord's last name
   * @param email landlord's email
   * @param password landlord's password
   * @param address landlord's address
   */
  public static void register(String firstName, String lastName, String email, String password, String address) {
    Logger.info("New Landlord: " + firstName + " " + lastName + " Email: " + email
        + " Password: " + password);
    Landlord user = new Landlord(firstName, lastName, email, password, address);
    user.save();
    if ((session.get("logged_in_landlord") == null) && (session.get("logged_in_admin") != null)) {
      Administrators.index();
    } else {
      login();
    }
  }

  /**
   * Method to authenticate a landlord
   * 
   * @param email The input email
   * @param password The input password
   */
  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate landlord with: " + email + " " + password);
    Landlord checkUser = Landlord.findByEmail(email);
    if ((checkUser != null) && (checkUser.checkPassword(password) == true)) {
      session.put("logged_in_landlord", checkUser.id);
      Logger.info("Authentication successful");
      Logger.info(checkUser.firstName + " " + checkUser.lastName + " logging in...");
      LandlordConfig.index();
    } else {
      Logger.info("Authentication failed");
      login();
    }
  }

  /**
   * If a landlord is logged in, returns that user
   * 
   * @return The currently logged in user
   */
  public static Landlord getCurrentLandlord() {
    String userId = session.get("logged_in_landlord");
    if (userId == null || userId.isEmpty()) {
      Home.index();
      return null;
    } else {
      Landlord currentUser = Landlord.findById(Long.parseLong(userId));
      return currentUser;
    }
  }

  /**
   * Logs out current landlord, returns to the home page
   */
  public static void logout() {
    Landlord user = getCurrentLandlord();
    if (user != null) {
      Logger.info(user.firstName + " " + user.lastName + " logging out...");
      session.remove("logged_in_landlord");
    }
    Home.index();
  }
}
