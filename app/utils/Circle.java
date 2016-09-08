package utils;

/**
 * @file Circle.java
 * @brief Class to instantiate a circle and methods to return it's properties
 * @author michaelfoy
 * @version 2016-06-16
 */
public class Circle {

  public double radius;
  public double latcentre;
  public double lngcentre;

  /**
   * Returns the centre of the circle as a LatLng object
   * 
   * @return the centre of the circle
   */
  public LatLng getCentre() {
    return new LatLng(latcentre, lngcentre);
  }

  /**
   * Returns the radius of the circle
   * 
   * @return the radius of the circle
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Constructor to instantiate circle
   * 
   * @param lat the centre of the circle's latitude coordinate
   * @param lng the centre of the circle's longitude coordinate
   * @param radius the radius of the circle
   */
  public Circle(double lat, double lng, double radius) {
    this.radius = radius;
    this.latcentre = lat;
    this.lngcentre = lng;
  }
}
