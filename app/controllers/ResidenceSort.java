package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import javax.swing.text.html.HTML;
import models.*;
import utils.CheapRentComparator;
import utils.ExpensiveRentComparator;
import utils.LandlordComparator;
import utils.RentalStatusComparator;
import utils.ResidenceTypeComparator;

import java.util.Collections;
import java.util.List;
/**
 * @file ResidenceSort.java
 * @brief Controller for administrator report sorting buttons
 * @author michaelfoy
 * @version 2016-08-17 
 */
public class ResidenceSort extends Controller {
  
  //Switch to toggle rent sort
  static int rent = 1;
  
  /**
   * Sorts all registered residences by whether occupied or not
   */
  public static void rentalStatus() {
    List<Residence> residences = Residence.findAll();
    Collections.sort(residences, new RentalStatusComparator());
    Logger.info("Residences sorted by rental status");
    render(residences);
  }
  
  /**
   * Sorts all registered residences by rent in ascending/ descending order 
   */
  public static void rent() {
    List<Residence> residences = Residence.findAll();
    if (rent % 2 == 0) {
      Collections.sort(residences, new ExpensiveRentComparator());
      rent++;
    } else {
      Collections.sort(residences, new CheapRentComparator());
      rent--;
    }
    Logger.info("Residences sorted by rent");
    render(residences);
  }
  
  /**
   * Sorts all registered residences by property type
   */
  public static void propertyType() {
    List<Residence> residences = Residence.findAll();
    Collections.sort(residences, new ResidenceTypeComparator());
    Logger.info("Residences sorted by property type");
    render(residences);
  }
  
  /**
   * Sorts Residence objects by landlord's first name
   * 
   * @return Ordered list of Residence objects
   */
  public static List sortLandlords() {
    List<Residence> residences = Residence.findAll();
    Collections.sort(residences, new LandlordComparator());
    return residences;
  }

}
 