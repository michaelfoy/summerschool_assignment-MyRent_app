import java.io.*;

import controllers.Home;
import play.*;
import play.jobs.*;
//import play.libs.MimeTypes;
import play.test.*;

/**
 * @file Bootstrap.java
 * @brief Clears database and loads yml file containing default data
 * @author michaelfoy
 * @version 2016-06-16
 *
 */

@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");
  }
}