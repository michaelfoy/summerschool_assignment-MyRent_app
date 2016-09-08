package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file MessageController.java
 * @brief Controller for message pages
 * @author michaelfoy
 * @version 2016-07-30
 */
public class MessageController extends Controller {

  public static void index() {
    if ((session.get("logged_in_landlord") == null) && (session.get("logged_in_tenant") == null)) {
      Logger.info("Neither landlord, nor tenant logged in");
      Home.index();
    } else {
      render();
    }
  }
  /**
   * Loads the message page if a landlord logged in
   */
  public static void landlord() {
    String landlordId = session.get("logged_in_landlord");
    if (landlordId != null) {
      render();
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * Loads the message page if a tenant logged in
   */
  public static void tenant() {
    String tenantId = session.get("logged_in_tenant");
    if (tenantId != null) {
      render();
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * Loads the acknowledgement page
   */
  public static void acknowledge() {
    render();
  }
  
  /**
   * 
   */
  public static void config() {
    if((session.get("logged_in_landlord") == null) && (session.get("logged_in_tenant") == null)) {
      Logger.info("No user logged in");
      Home.index();
    } else if ((session.get("logged_in_landlord") != null) && (session.get("logged_in_tenant") != null)) {
      Logger.info("Both landlord and tenant logged in");
      session.clear();
      Home.index();
    } else if (session.get("logged_in_landlord") == null) {
      TenantConfig.index();
    } else {
      LandlordConfig.index();
    }
  }
  /**
   * Saves the message and calls the acknowledgement page
   */
  public static void send(String firstName, String lastName, String email, String messageText) {
    String name = firstName + " " + lastName;
    Message message = new Message(name, email, messageText);
    message.save();
    Logger.info("New Message sent from " + firstName + " " + lastName);
    acknowledge(); 
  }
}
