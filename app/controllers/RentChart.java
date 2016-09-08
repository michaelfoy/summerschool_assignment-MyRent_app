package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;

/**
 * @file RentChart.java
 * @brief Controller for administrator chart page
 * @author michaelfoy
 * @version 2016-08-17
 */
public class RentChart extends Controller {

  /**
   * Loads rent chart page if administrator logged in
   */
  public static void index() {
    if (session.get("logged_in_admin") != null) {
      List<Residence> residences = ResidenceSort.sortLandlords();
      render(residences);
    } else {
      Home.index();
    }
  }

  /**
   * Formats landlord rent data as a JSON string to be used in google pie chart Data includes:
   * landlord's name,
   * income percentage relative to myrent portfolio,
   * landlord's total rental income
   */
  public static void chartData() {
    List<Landlord> landlords = Landlord.findAll();
    List<List<String>> data = new ArrayList();

    // For each landlord, calculates total monthly rent
    for (Landlord llord : landlords) {
      List<Residence> residences = llord.properties;
      List<String> llordData = new ArrayList<String>();
      int totalRent = 0;
      for (Residence res : residences) {
        totalRent += res.rent;
      }

      float percent = percentage(totalRent);
      llordData.add(llord.firstName + " " + llord.lastName);
      llordData.add("" + percent);
      llordData.add("" + totalRent);
      data.add(llordData);
    }
    renderJSON(data);
  }

  /**
   * Calculates income as a percentage of total myrent portfolio
   * 
   * @param rent An individual landlord's total rental income
   * @return The percentage the income represents of myrent portfolio
   */
  public static float percentage(int rent) {
    float total = 0;
    List<Residence> residences = Residence.findAll();
    for (Residence res : residences) {
      total += res.rent;
    }
    float result = (rent / total) * 100;
    return result;
  }
}
