package controllers;

import play.*;
import play.mvc.*;
import java.util.List;
import java.util.ArrayList;
import models.*;
import utils.Circle;
import utils.LatLng;
import utils.Geodistance;

/**
 * @file Report.java
 * @brief Controller for delivering landlord reports
 * @author michaelfoy
 * @version 2016-06-16
 */
public class Report extends Controller {

  /**
   * Loads the report map page if a landlord is logged in
   */
  public static void index() {
    String userId = session.get("logged_in_landlord");
    if (userId != null) {
      render();
    } else {
      Logger.info("Redirecting to home page");
      Home.index();
    }
  }
  
  /**
   * Loads report page for administrator if logged in
   * 
   * @param residences All registered residences
   */
  public static void adminReport() {
    String userId = session.get("logged_in_admin");
    if (userId == null) {
      Logger.info("Redirecting to home page");
      Home.index();
    } else {
      List<Residence> residences = Residence.findAll();
      render(residences);
    }
  }

  /**
   * Finds all residences within a given circle
   * 
   * @param radius Radius of the search area
   * @param lat Lateral coordinates of the centre of the circle
   * @param lng Longitudinal coordinates of the centre of the circle
   */
  public static void generate(String radius, String lat, String lng) {

    List<Residence> residences = Residence.findAll();
    List<Residence> results = iterateResidences(radius, lat, lng, residences);
    Circle area = new Circle(Double.valueOf(lat), Double.valueOf(lng), Double.valueOf(radius));
    Landlord user = Landlords.getCurrentLandlord();
    String userName = user.firstName + " " + user.lastName;

    Logger.info("Report being compiled for " + userName);
    render(userName, area, results);
  }

  /**
   * Finds all vacant residences within a given circle
   * 
   * @param radius Radius of the search area
   * @param lat Lateral coordinates of the centre of the circle
   * @param lng Longitudinal coordinates of the centre of the circle
   */
  public static void generateVacant(String radius, String lat, String lng) {
    List<Residence> vacants = TenantConfig.findVacants();
    List<Residence> results = iterateResidences(radius, lat, lng, vacants);
    Circle area = new Circle(Double.valueOf(lat), Double.valueOf(lng), Double.valueOf(radius));
    Tenant user = Tenants.getCurrentTenant();
    String userName = user.firstName + " " + user.lastName;

    Logger.info("Report of vacants being compiled for " + userName);
    render(userName, area, results);
  }

  /**
   * Iterates through a List of given Residence objects to see if they fall within the radius of the
   * search area. All Residences which do are returned to the html page
   * 
   * @param radius Radius of the search area
   * @param lat Lateral coordinates of the centre of the circle
   * @param lng Longitudinal coordinates of the centre of the circle
   * @param residences List of residence objects
   * @return List of residences which fall within the search area
   */
  public static List iterateResidences(String radius, String lat, String lng,
      List<Residence> residences) {
    List<Residence> results = new ArrayList<Residence>();
    Circle area = new Circle(Double.valueOf(lat), Double.valueOf(lng), Double.valueOf(radius));

    for (Residence res : residences) {
      String llString = res.location;
      LatLng latlng = LatLng.toLatLng(llString);
      if (Geodistance.inCircle(latlng, area)) {
        results.add(res);
      }
    }
    return results;
  }
}
