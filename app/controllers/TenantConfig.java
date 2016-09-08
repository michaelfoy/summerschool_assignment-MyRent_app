package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file TenantConfig.java
 * @brief Controller for pages for tenant profile configuration
 * @author michaelfoy
 * @version 2016-30-07
 */
public class TenantConfig extends Controller {
  
  /**
   * If Tenant logged in, loads configuration page
   */
  public static void index() {
    String userId = session.get("logged_in_tenant");
    String eircode;
    String name;
    if (userId != null) {
      Tenant user = Tenants.getCurrentTenant();
      name = user.firstName;
      if (user.residence != null) {
        eircode = user.residence.eircode;
      } else { 
        eircode = "";
      }
      List<Residence> vacantProperties = findVacants();
      render(name, eircode, vacantProperties);
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * Allows a user to cancel their tenancy
   * 
   * @param eircode Eircode of the residence
   */
  public static void deleteTenancy(String eircode) {
    //String eircode = formData.substring(8);
    Residence res = Residence.findByEircode(eircode);
    Logger.info("Terminating tenancy of " + res.tenant.firstName + " " + res.tenant.lastName + " at " + eircode);
    res.tenant = null;
    res.save();
    index();
    //List<List<String>> data = getVacants();
    //renderJSON(data);
  }
  
  /**
   * Allows a user to create a new tenancy
   * 
   * @param residenceId Id of the residence for new tenancy
   */
  public static void newResidence(String id) {
    Tenant tenant = Tenants.getCurrentTenant();
    if (tenant.residence != null) {
      index();
    } else{
      Long resId = Long.parseLong(id);
      Residence residence = Residence.findById(resId);
      Logger.info(tenant.firstName + " " + tenant.lastName + " taking up tenancy at " + residence.eircode);
      residence.tenant = tenant;
      residence.save();
      tenant.residence = residence;
      tenant.save();
      index();
    }
  }
  
  /**
   * Formats vacant residence data into a list of String for each residence,
   * these are returned in a List
   * 
   * @return List of lists of data for each vacant residence
   */
  public static List getVacants() {
    List<List<String>> data = new ArrayList<List<String>>();
    List<Residence> vacants = findVacants();
    for (Residence res : vacants) {
      List<String> resData = new ArrayList<String>();
      resData.add(res.location);
      resData.add(res.eircode);
      resData.add("" + res.rent);
      resData.add("" + res.id);
      data.add(resData);
    }
    return data;
  }
  
  /**
   * Returns a list of all vacant properties
   * 
   * @return List of vacant properties
   */
  public static List findVacants() {
    List<Residence> properties = Residence.findAll();
    List<Residence> vacantProperties = new ArrayList<Residence>();
    for (Residence res : properties) {
      if (res.tenant == null) {
        vacantProperties.add(res);
      }
    }
    return vacantProperties;
  }
  
  /**
   * Method to send AJAX JSON response containing vacant residence data
   */
  public static void getVacantsData() {
    List<List<String>> data = getVacants();
    renderJSON(data);
  }
}

