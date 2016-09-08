package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import javax.swing.text.html.HTML;
import models.*;
import java.util.List;
import java.util.ArrayList;

/**
 * @file Administrators.java
 * @brief Controller for pages for Administrators
 * @author michaelfoy
 * @version 2016-08-09
 */
public class Administrators extends Controller {

  /**
   * Loads log in page
   */
  public static void login() {
    render();
  }

  /**
   * Loads index page if Administrator logged in
   */
  public static void index() {
    if (session.get("logged_in_admin") == null) {
      session.clear();
      Home.index();
    } else {
      List<Residence> residences = Residence.findAll();
      List<Tenant> tenants = Tenant.findAll();
      List<Landlord> landlords = Landlord.findAll();
      render(residences, tenants, landlords);
    }
  }

  /**
   * Loads register Tenant page if admin logged in
   */
  public static void registerTenant() {
    if (session.get("logged_in_admin") == null) {
      session.clear();
      Home.index();
    } else {
      render();
    }
  }

  /**
   * Loads register Landlord page if landlord logged in
   */
  public static void registerLandlord() {
    if (session.get("logged_in_admin") == null) {
      session.clear();
      Home.index();
    } else {
      render();
    }
  }

  /**
   * Deletes a tenant, sends a JSON AJAX response containing updated map data
   * 
   * @param formData String containing tenant id (format: "tenant=id")
   */
  public static void deleteTenant(String formData) {
    String id = formData.substring(7);
    Long tenantId = Long.parseLong(id);
    Tenant tenant = Tenant.findById(tenantId);

    Logger.info("Deleting tenant: " + tenant.firstName + " " + tenant.lastName);
    Residence res = tenant.residence;
    res.tenant = null;
    res.save();
    tenant.delete();
    ajaxDeleteResponse(id);
  }

  /**
   * Deletes a landlord, sends a JSON AJAX response containing updated map data
   * 
   * @param formData String containing landlord id (format: "landlord=id")
   */
  public static void deleteLandlord(String formData) {
    String id = formData.substring(9);
    Long landlordId = Long.parseLong(id);
    Landlord landlord = Landlord.findById(landlordId);
    landlord.delete();
    ajaxDeleteResponse(id);
  }
  
  /**
   * Sends AJAX JSON response containing residence data and user id following a delete
   * 
   * @param id Id of deleted Tenant or Landlord
   */
  public static void ajaxDeleteResponse(String id) {
    List<List<String>> locations = geoLoc();
    List<String> deletedId = new ArrayList();
    deletedId.add(id);
    locations.add(0, deletedId);
    renderJSON(locations);
  }

  /**
   * Sends residence data for admin map as JSON AJAX response
   */
  public static void getLocations() {
    renderJSON(geoLoc());
  }

  /**
   * Formats residence data to be sent asynchronously to js files Creates an array of arrays, each
   * of which contains: [0] location coordinates [1] eircode [2] tenant first name and surname
   */
  public static List geoLoc() {
    List<List<String>> locations = new ArrayList<List<String>>();
    List<Residence> residences = Residence.findAll();
    String tenant;
    for (int i = 0; i < residences.size(); i++) {
      Residence res = residences.get(i);
      if (res.tenant != null) {
        tenant = res.tenant.firstName + " " + res.tenant.lastName;
      } else {
        tenant = "Not occupied";
      }
      locations.add(i, Arrays.asList(res.location, res.eircode, tenant));
    }
    return locations;
  }

  /**
   * Method to authenticate an administrator
   * 
   * @param email The input email
   * @param password The input password
   */
  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate administrator with: " + email + " " + password);
    Administrator checkUser = Administrator.findByEmail(email);
    if ((checkUser != null) && (checkUser.checkPassword(password) == true)) {
      session.put("logged_in_admin", checkUser.id);
      Logger.info("Authentication successful");
      Logger.info("Administrator logging in...");
      index();
    } else {
      Logger.info("Authentication failed");
      Home.index();
    }
  }

  /**
   * Logs out Administrator, returns to the home page
   */
  public static void logout() {
    if (session.get("logged_in_admin") != null) {
      Logger.info("Administrator logging out...");
      session.remove("logged_in_admin");
    }
    Home.index();
  }
}
