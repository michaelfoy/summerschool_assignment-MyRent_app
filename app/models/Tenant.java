package models;

import play.db.jpa.Model;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @file Tenant.java
 * @brief Class which describes a Tenant
 * @author michaelfoy
 * @version 2016-07-27
 */
@Entity
public class Tenant extends Model {
  public String email;
  public String firstName;
  public String lastName;
  public String password;

  @OneToOne(mappedBy = "tenant")
  public Residence residence;

  /**
   * Constructs a tenant object
   * 
   * @param firstName tenant's first name
   * @param lastName tenant's last name
   * @param email tenant's email
   * @param password tenant's password
   */
  public Tenant(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  /**
   * Finds a tenant using their email address
   * 
   * @param email The email address of the tenant being searched
   * @return The relevant tenant for the email address
   */
  public static Tenant findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * Checks if a given password matches a tenant's password
   * 
   * @param password The password to be checked
   * @return True if the password is correct for this tenant
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
