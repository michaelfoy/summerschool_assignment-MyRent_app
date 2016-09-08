package story_tests;

import org.junit.*;
import models.Landlord;
import play.test.*;

/**
 * @file Story03.java
 * @brief JUnit test class for Story 3
 * @author michaelfoy
 * @version 2016-07-26
 */
public class Story03 extends UnitTest {
  
  @Test
  public void testCreate() {
    //Positive Test
    Landlord landlord = Landlord.findByEmail("homer@simpson.com");
    assertEquals("Homer", landlord.firstName);
    
    //Negative Test
    assertNull(Landlord.findByEmail("grandad@simpson.com"));
  }
}
