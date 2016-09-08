package story_tests;

import org.junit.*;
import models.Residence;
import play.test.*;

/**
 * @file Story09.java
 * @brief JUnit test class for Story 9
 * @author michaelfoy
 * @version 2016-07-30
 */
public class Story09 extends UnitTest {

  Residence residence;
  Residence residence2;
  
  @Before
  public void setup() {  
    residence = Residence.findByEircode("ABC1234");
    residence2 = Residence.findByEircode("MNO1234");
  }
  
  @Test
  public void testCreate() {
    //Positive Test
    assertNotNull(residence.tenant);
    
    //Negative Test
    assertNull(residence2.tenant);
  }
}