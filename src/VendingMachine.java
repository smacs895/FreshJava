import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/** Class for the Vending Machine.
 *
 */
public class VendingMachine{

  private static StringBuffer stringBufferOfData = new StringBuffer();
  private static String filename = null;

  //Create ArrayLists of products.
  ArrayList<Product> products = readProduct();
  private ArrayList<Product> allProducts = new ArrayList<>();

  //Create new instance of Client.
  private Client client = new Client();


  /** Method to read the Products.dat file in.
   *
   * @return products - an ArrayList of the information in the Products.dat file.
   */
  public ArrayList<Product> readProduct(){
    Scanner fileToRead = null;
    ArrayList<String> stringProducts = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();

    try{
      fileToRead = new Scanner(new File("Product.dat"));

      for(String line; fileToRead.hasNextLine() && (line = fileToRead.nextLine()) != null; ){

        stringProducts.add(line);
        stringBufferOfData.append(line).append("\r\n");
      }
      for(int i = 0; i < stringProducts.size(); i++){
        String[] temp = stringProducts.get(i).split(",");
        products.add(new Product(temp[0],temp[1],Double.parseDouble(temp[2]),Integer.parseInt(temp[3])));
      }
      fileToRead.close();
    }catch(FileNotFoundException ex){
      System.out.println("The file " + filename + " could not be found! " + ex.getMessage());
    }finally{
      fileToRead.close();
    }
    return products;
  }


  /** Method to buy a product from the Vending Machine.
   *  This sets the new quantity of the product and sets the new credit balance for the user.
   *  Also writes this new information to both the Product.dat and Client.dat files.
   *
   * @param description
   */
  public void buyProduct(String description){
    for(int i = 0; i < products.size(); i++){
      if(description.equalsIgnoreCase(products.get(i).getDescription())){
        if(products.get(i).getQuantity() >= 1){
          products.get(i).setQuantity(products.get(i).getQuantity() - 1);
          System.out.println("There are now " + products.get(i).getQuantity() + " " + products.get(i).getDescription() + " in stock.");
          writeToProduct();
          client.writeClient();
        }else{
          System.out.println(products.get(i).getDescription() + " is out of stock!");
        }
      }
    }
  }


  /** Method to restock a product in the Vending Machine.
   *  This sets the new quanitity of the product that is restocked and writes this to the Product.dat file.
   *
   * @param description
   */
  public void restockProduct(String description){
    for(int i = 0; i < products.size(); i++){
      if(description.equalsIgnoreCase(products.get(i).getDescription())){
          products.get(i).setQuantity(products.get(i).getQuantity() + 10);
          System.out.println("There are now " + products.get(i).getQuantity() + " " + products.get(i).getDescription() + " in stock.");
          writeToProduct();
        }
    }
  }


  /** Method to add a new product to the Vending Machine.
   *  This writes the new product to the Product.dat file.
   * @param p
   */
  public void addNewProduct(Product p){
    readProduct();
    products.add(p);
    writeToProduct();
  }


  /** Method to write to the Product.dat file.
   *
   */
  public void writeToProduct(){
    try{
      PrintWriter print = new PrintWriter(new FileOutputStream("Product.dat"));
      for(int i = 0; i < products.size(); i++){
        print.println(products.get(i).toString());
      }
      print.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }


  /** Method to read the buttons, used to display the products on the BuyProductGUI and RestockProductGUI.
   *
   * @return allButtons - an ArrayList of the buttons.
   */
  public ArrayList<Button> readButtons(){
    String name;
    String location;
    double price;
    int quantity;

    ArrayList<String> products = new ArrayList<>();
    ArrayList<Button> allButtons = new ArrayList<>();

    try{
      BufferedReader br = new BufferedReader(new FileReader("Product.dat"));
      String prod;

      while((prod = br.readLine()) != null){
        products.add(prod);
      }

      for(int i = 0; i < products.size(); i++){
        String[] temp = (products.get(i).split(","));
        name = temp[0];
        location = temp[1];
        price = Double.parseDouble(temp[2]);
        quantity = Integer.parseInt(temp[3]);
        allProducts.add(new Product(name,location,price,quantity));
      }
    }catch(IOException e){
      e.printStackTrace();
    }
    int i;
    for(i = 0; i < allProducts.size(); i++){
      allButtons.add(new Button(allProducts.get(i).getDescription()));
    }
    return allButtons;
  }
}