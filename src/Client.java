import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/** Class for the Client.
 *  The class extends User, which is an abstract class with abstract methods: checkLogin, indexOfUser and readFile.
 */
public class Client extends User{
  private double credit = 0.0;
  ArrayList<String> clients = readFile();

  /** Default Constructor
   *
   */
  public Client(){
  }


  /**
   * Constructor for Client.
   *
   * @param username of the client.
   * @param password of the client.
   */
  public Client(String username,String password){
    this.username = username;
    this.password = password;
  }


  /**
   * Method to get the Username of the client.
   *
   * @return username
   */
  public  String getUsername(){
    return username;
  }


  /** Method to get the username, credit and password of the Client.
   *
   * @param username
   */
  public Client(String username){
    this.username = username;
    String temp[] = clients.get(indexOfUser()).split(",");
    credit = Double.parseDouble(temp[1]);
    password = temp[2];
  }


  /**
   * Method to read the Client.dat file.
   *
   * @return clients - an ArrayList of the file.
   */
  public ArrayList<String> readFile(){
    ArrayList<String> clients = new ArrayList<String>();
    try{
      FileReader fr = new FileReader("Client.dat");
      BufferedReader br = new BufferedReader(fr);
      String str = "";

      while((str = br.readLine()) != null){
        clients.add(str);
      }
      br.close();
    }catch(IOException e){
      e.printStackTrace();
    }
    return clients;
  }


  /**
   * Method to get the index position of the user. (Abstract method in User)
   * Declares a temp String array 'tempClient' and then uses regex to split from the comma
   *
   * @return index - the index position of the admin.
   */
  public int indexOfUser(){
    readFile();
    int index = -1;

    for(int i = 0; i < clients.size(); i++){
      String[] tempClient = (clients.get(i).split(","));
      if(tempClient[0].equalsIgnoreCase(username)){
        index = i;
      }
    }
    return index;
  }


  /**
   * Method to check the Log in of the client (Abstract method in User).
   *
   * @return valid if the password entered matches the admins password on the Client.dat file.
   */
  @Override
  public boolean checkLogin(){
    boolean valid;
    readFile();
    String[] temp = clients.get(indexOfUser()).split(",");

    if(temp[2].equalsIgnoreCase(password)){
      valid = true;
    }else{
      valid = false;
    }
    return valid;
  }


  /** Getter for credit
   *
   * @return credit
   */
  public double getCredit(){
    return credit;
  }


  /** Setter for credit
   *
   * @param credit
   */
  public void setCredit(double credit){
    this.credit = credit;
  }


  /** To String method to print nicely.
   *
   * @return the username + credit + password.
   */
  @Override
  public String toString(){
    return username + "," + credit + "," + password;
  }


  /** Method to write to the Client.dat file.
   *
   */
  public void writeClient(){
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    clients.set(indexOfUser(), username + "," + decimalFormat.format(getCredit()) + "," + password);
    try{
      PrintWriter printer = new PrintWriter(new FileOutputStream("Client.dat"));
      for(int i = 0; i< clients.size(); i++){
        printer.println(clients.get(i));
      }
      printer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
