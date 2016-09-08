package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import controllers.Landlords;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @file Residence.java
 * @brief Class which describes a residence object
 * @author michaelfoy
 * @version 2016-06-16
 */
@Entity
public class Residence extends Model {
  public String location;
  public String eircode;
  public int rent;
  public int bedrooms;
  public int numberBathrooms;
  public int area;
  public String residenceType;
  public String dateRegistered;

  @ManyToOne
  public Landlord landlord;
  
  @OneToOne
  public Tenant tenant;

  /**
   * Constructor for residence object
   * 
   * @param location The coordinates of the property location
   * @param eircode Eircode of the property
   * @param rent The rental rate
   * @param bedrooms The number of bedrooms
   * @param numberBathrooms The number of bathrooms
   * @param area The area of the property in metres squared
   * @param residenceType The type of property it is
   */
  public Residence(String location, String eircode, int rent, int bedrooms, int numberBathrooms, int area, String residenceType) {
    this.location = location;
    this.eircode = eircode;
    this.rent = rent;
    this.bedrooms = bedrooms;
    this.numberBathrooms = numberBathrooms;
    this.area = area;
    this.residenceType = residenceType;
    this.landlord = Landlords.getCurrentLandlord();
    editDate();
  }
  
  /**
   * Constructor for JUnit Test
   * Unable to authenticate user from test class
   */
  public Residence(String eircode, int numberBathrooms, int area) {
    this.eircode = eircode;
    this.numberBathrooms = numberBathrooms;
    this.area = area;
  }
  
  /**
   * Formats the date input for output
   */
  public void editDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy");
    Date today = new Date();
    this.dateRegistered = formatter.format(today); 
  }

  /**
   * Finds a residence by it's eircode
   * 
   * @param eircode The residence's eircode
   * @return The residence object
   */
  public static Residence findByEircode(String eircode) {
    return find("eircode", eircode).first();
  }
}
