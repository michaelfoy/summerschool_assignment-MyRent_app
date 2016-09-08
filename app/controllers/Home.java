package controllers;

import play.*;
import play.mvc.*;
import models.*;

/**
 * @file Home.java
 * @brief Controller for landing page
 * @author michaelfoy
 * @version 2016-06-16
 */
public class Home extends Controller  {
  
  public static void index()  {
    Logger.info("Landed in Welcome class");
    render();
  }
}
