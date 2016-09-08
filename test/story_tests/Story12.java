package story_tests;

import org.junit.*;

import controllers.Administrators;
import models.Administrator;
import play.test.*;

/**
 * @file Story12.java
 * @brief JUnit test class for Story 12
 * @author michaelfoy
 * @version 2016-08-09
 */
public class Story12 extends UnitTest {

  Administrator admin;
  
  @Before
  public void setup() {
    admin = new Administrator();
    admin.save();
  }
  
  @After
  public void takeDown() {
    admin.delete();
  }
  
  @Test
  public void testCreate() {
    //Positive Test
    assertNotNull(admin.email);
    assertEquals(admin.findByEmail("admin@witpress.com"), admin);
  }
}
