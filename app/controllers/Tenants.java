package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file Tenants.java
 * @brief Controller for pages for tenants
 * @author michaelfoy
 * @version 2016-06-16
 */
public class Tenants extends Controller {

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
   * @param firstName tenant's first name
   * @param lastName tenant's last name
   * @param email tenant's email
   * @param password tenant's password
   */
  public static void register(String firstName, String lastName, String email, String password) {
    Logger.info("New tenant: " + firstName + " " + lastName + " Email: " + email
        + " Password: " + password);
    Tenant user = new Tenant(firstName, lastName, email, password);
    user.save();
    if ((session.get("logged_in_tenant") == null) && (session.get("logged_in_admin") != null)) {
      Administrators.index();
    } else {
      login();
    }
  }

  /**
   * Method to authenticate a tenant
   * 
   * @param email The input email
   * @param password The input password
   */
  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate tenant with: " + email + " " + password);
    Tenant checkUser = Tenant.findByEmail(email);
    if ((checkUser != null) && (checkUser.checkPassword(password) == true)) {
      session.put("logged_in_tenant", checkUser.id);
      Logger.info("Authentication successful");
      Logger.info(checkUser.firstName + " " + checkUser.lastName + " logging in...");
      TenantConfig.index();
    } else {
      Logger.info("Authentication failed");
      login();
    }
  }

  /**
   * If a tenant is logged in, returns that user
   * 
   * @return The currently logged in user
   */
  public static Tenant getCurrentTenant() {
    String userId = session.get("logged_in_tenant");
    if (userId == null || userId.isEmpty()) {
      Home.index();
      return null;
    } else {
      Tenant currentUser = Tenant.findById(Long.parseLong(userId));
      return currentUser;
    }
  }

  /**
   * Logs out current tenant, returns to the home page
   */
  public static void logout() {
    Tenant user = getCurrentTenant();
    if (user != null) {
      Logger.info(user.firstName + " " + user.lastName + " logging out...");
      session.remove("logged_in_tenant");
    }
    Home.index();
  }

  /**
   * If tenant logged in, loads edit profile page
   */
  public static void editprofile() {
    String userId = session.get("logged_in_userid");
    if (userId != null) {
      Tenant user = getCurrentTenant();
      render(user);
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
}
