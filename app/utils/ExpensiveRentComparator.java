package utils;

import java.util.Comparator;
import models.Residence;
/**
 * @file ExpensiveRentComparator.java
 * @brief Comparator to sort rent from highest to lowest
 * @author michaelfoy
 * @version 2016-08-17
 */
public class ExpensiveRentComparator implements Comparator<Residence>{
  
  @Override
  public int compare(Residence res1, Residence res2) {
    Integer rent1 = res1.rent;
    Integer rent2 = res2.rent;
    return rent2.compareTo(rent1);
  }
}
