package utils;

import java.util.Comparator;
import models.Residence;
/**
 * @file RentalStatusComparator.java
 * @brief Comparator to sort residences by whether or not they are occupied
 * @author michaelfoy
 * @version 2016-08-17
 */
public class RentalStatusComparator implements Comparator<Residence>{
  
  @Override
  public int compare(Residence res1, Residence res2) {
    Integer tenant1 = 0;
    Integer tenant2 = 0;
    if ( res1.tenant != null ) { tenant1 = 1; }
    if ( res2.tenant != null ) { tenant2 = 1; } 
    return tenant2.compareTo(tenant1);
  }
}
