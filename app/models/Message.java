package models;

import play.db.jpa.Model;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @file Message.java
 * @brief Class which describes a message object
 * @author michaelfoy
 * @version 2016-06-16
 */
@Entity
public class Message extends Model {

  public String messageText;
  public String sender;
  public String email;
  public Date dateSent;

  /**
   * Constructor for message object
   * 
   * @param sender Message sender
   * @param email Email address of message sender
   * @param messageText Text of the message
   */
  public Message(String sender, String email, String messageText) {
    this.sender = sender;
    this.messageText = messageText;
    this.email = email;
    this.dateSent = new Date();
  }
}
