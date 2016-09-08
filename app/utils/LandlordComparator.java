package utils;

import java.util.Comparator;
import models.Residence;
/**
 * @file LandlordComparator.java
 * @brief Comparator to sort residences by their landlord's name
 * @author michaelfoy
 * @version 2016-08-18
 */
public class LandlordComparator implements Comparator<Residence>{
  
  @Override
  public int compare(Residence res1, Residence res2) {
    String tenant1 = res1.landlord.firstName;
    String tenant2 = res2.landlord.firstName;
    return tenant1.compareToIgnoreCase(tenant2);
  }
}
