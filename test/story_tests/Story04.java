package story_tests;

import org.junit.*;
import models.Landlord;
import play.test.*;

/**
 * @file Story04.java
 * @brief JUnit test class for Story 4
 * @author michaelfoy
 * @version 2016-07-27
 */
public class Story04 extends UnitTest {
  
  @Test
  public void testCreate() {
    //Positive Test
    Landlord landlord = Landlord.findByEmail("homer@simpson.com");
    assertEquals("138 Nice St., Springfield, USA", landlord.address);
    
    //Negative Test
    assertNull(Landlord.findByEmail("grandad@simpson.com"));
  }
}