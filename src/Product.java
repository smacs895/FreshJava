
/** Class for the Products.
 *
 */
public class Product{
  private String description = "";
  private String location = "";
  private double price = 0.0;
  private int quantity = 0;

  /** Default Constructor
   *
   */
  public Product(){
  }



  /** Overloaded Constructor
   *
   * @param description
   * @param location
   * @param price
   * @param quantity
   */
  public Product(String description,String location,double price,int quantity){
    this.description = description;
    this.location = location;
    this.price = price;
    this.quantity = quantity;
  }


  /** Getter for Description
   *
   * @return description
   */
  public String getDescription(){
    return description;
  }


  /** Setter for Description
   *
   * @param description
   */
  public void setDescription(String description){
    this.description = description;
  }


  /** Getter for Location
   *
   * @return location
   */
  public String getLocation(){
    return location;
  }


  /** Setter for Location
   *
   * @param location
   */
  public void setLocation(String location){
    this.location = location;
  }


  /** Getter for price
   *
   * @return price
   */
  public double getPrice(){
    return price;
  }


  /** Setter for price
   *
   * @param price
   */
  public void setPrice(double price){
    this.price = price;
  }


  /** Getter for quantity
   *
   * @return quantity
   */
  public int getQuantity(){
    return quantity;
  }


  /** Setter for quantity
   *
   * @param quantity
   */
  public void setQuantity(int quantity){
    this.quantity = quantity;
  }


  /** To String method to print nicely.
   *
   * @return the description + location + price + quantity.
   */
  @Override
  public String toString(){
    return description + "," + location + "," + price + "," + quantity;
  }
}