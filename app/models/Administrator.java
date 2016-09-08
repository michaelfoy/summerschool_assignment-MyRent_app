package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * @file Administrator.java
 * @brief Describes an administrator 
 * @author michaelfoy
 * @version 2016-08-09
 */
@Entity
public class Administrator extends Model {
  public String email;
  public String password;
  
  public Administrator() {
    this.email = "admin@witpress.ie";
    this.password = "secret";
  }
  
  /**
   * Finds an administrator using their email address
   * 
   * @param email The email address of the administrator being searched
   * @return The relevant administrator for the email address
   */
  public static Administrator findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * Checks if a given password matches an administrator password
   * 
   * @param password The password to be checked
   * @return True if the password is correct for this administrator
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
