package story_tests;

import org.junit.*;
import models.Landlord;
import models.Tenant;
import play.test.*;

/**
 * @file Story05.java
 * @brief JUnit test class for Story 5
 * @author michaelfoy
 * @version 2016-07-27
 */
public class Story05 extends UnitTest {

  Tenant tenant;
  @Before
  public void setup() {  
    tenant = new Tenant("Moe", "Syzlak", "mo@moman.com", "secret");
    tenant.save();
  }

  @After
  public void teardown() {   
    tenant.delete();
  }
  @Test
  public void testCreate() {
    //Positive Test
    Tenant tenant2 = Tenant.findByEmail("mo@moman.com");
    assertEquals(tenant2, tenant);
    
    //Negative Test
    assertNull(Tenant.findByEmail("grandad@simpson.com"));
  }
}