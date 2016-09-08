package story_tests;


import org.junit.*;
import models.Residence;
import play.test.*;

/**
 * @file Story01.java
 * @brief JUnit test class for Story 1
 * @author michaelfoy
 * @version 2016-07-26
 */
public class Story01 extends UnitTest {

  Residence residence;
  
  @Before
  public void setup() {  
    residence = new Residence("address", 2, 300);
    residence.save();
  }

  @After
  public void teardown() {   
    residence.delete();
  }
  
  @Test
  public void testCreate() {
    //Positive Tests
    assertEquals(residence.numberBathrooms, 2);
    assertEquals(residence.area, 300);
    //Negative Tests
    assertNotEquals(residence.numberBathrooms, "2");
    assertNotEquals(residence.area, "300");
  }
}
