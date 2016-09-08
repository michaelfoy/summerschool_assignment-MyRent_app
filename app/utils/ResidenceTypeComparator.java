package utils;

import java.util.Comparator;
import models.Residence;
/**
 * @file ResidenceTypeComparator.java
 * @brief Comparator to sort residences by their residence type
 * @author michaelfoy
 * @version 2016-08-17
 */
public class ResidenceTypeComparator implements Comparator<Residence>{
  
  @Override
  public int compare(Residence res1, Residence res2) {
    String tenant1 = res1.residenceType;
    String tenant2 = res2.residenceType;
    return tenant1.compareToIgnoreCase(tenant2);
  }
}
