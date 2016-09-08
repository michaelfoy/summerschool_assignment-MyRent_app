package utils;

/**
 * @file LatLng.java
 * @brief Class to describe a LatLng object which contains geographic coordinates
 * @author michaelfoy
 * @version 2016-06-16
 */
public class LatLng
{

  private double lat;
  private double lng;

  /**
   * Constructor for LatLng object
   * @param lat Lateral digit for coordinates
   * @param lng Longitudinal digit for coordinates
   */
  public LatLng(double lat, double lng)
  {
    this.lat = lat;
    this.lng = lng;
  }

  /**
   * Provides string representation of LatLng object
   */
  public String toString()
  {
    return lat + "," + lng;
  }

  /**
   * Translates a string containing coordinates into LatLng object
   * 
   * @param latlng
   *          : a string comprising a lat,lng : eg 53.444,-5.455
   * @return a LatLng object whose fields obtained by parsing argument
   */
  public static LatLng toLatLng(String latlng)
  {
    String[] latLng = latlng.split(",");
    return new LatLng(Double.parseDouble(latLng[0]), Double.parseDouble(latLng[1]));
  }

  /**
   * Getter for the latitude value of LatLng
   * 
   * @return Latitude value
   */
  public double getLatitude()
  {
    return lat;
  }

  /**
   * Getter for the longitudinal value of LatLng
   * 
   * @return Longitudinal value
   */
  public double getLongitude()
  {
    return lng;
  }

}