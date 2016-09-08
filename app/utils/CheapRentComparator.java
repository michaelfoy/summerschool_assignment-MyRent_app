package utils;

import java.util.Comparator;
import models.Residence;
/**
 * @file CheapRentComparator.java
 * @brief Comparator to sort rent from lowest to highest 
 * @author michaelfoy
 * @version 2016-08-17
 */
public class CheapRentComparator implements Comparator<Residence>{
  
  @Override
  public int compare(Residence res1, Residence res2) {
    Integer rent1 = res1.rent;
    Integer rent2 = res2.rent;
    return rent1.compareTo(rent2);
  }
}
