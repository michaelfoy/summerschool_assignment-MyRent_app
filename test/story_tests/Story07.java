package story_tests;

import org.junit.*;
import models.Residence;
import models.Residence;
import play.test.*;

/**
 * @file Story07.java
 * @brief JUnit test class for Story 7
 * @author michaelfoy
 * @version 2016-07-27
 */
public class Story07 extends UnitTest {

  Residence residence;
  
  @Before
  public void setup() {  
    residence = Residence.findByEircode("ABC1234");
    residence.delete();
  }
  
  @Test
  public void testCreate() {
    //Positive Test
    assertNotNull(Residence.findByEircode("DEF5678"));
    
    //Negative Test
    assertNull(Residence.findByEircode("ABC1234"));
  }
}