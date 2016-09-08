package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @file Landlord.java
 * @brief Class which describes a landlord
 * @author michaelfoy
 * @version 2016-06-16
 */
@Entity
public class Landlord extends Model {
  public String email;
  public String firstName;
  public String lastName;
  public String password;
  public String address;

  @OneToMany(mappedBy = "landlord", cascade=CascadeType.ALL)
  public List<Residence> properties = new ArrayList<Residence>();

  /**
   * Constructs a landlord object
   * 
   * @param firstName landlord's first name
   * @param lastName landlord's last name
   * @param email landlord's email
   * @param password landlord's password
   * @param address landlord's address
   */
  public Landlord(String firstName, String lastName, String email, String password, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
  }

  /**
   * Finds a landlord using their email address
   * 
   * @param email The email address of the landlord being searched
   * @return The relevant landlord for the email address
   */
  public static Landlord findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * Checks if a given password matches a landlord's password
   * 
   * @param password The password to be checked
   * @return True if the password is correct for this landlord
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
