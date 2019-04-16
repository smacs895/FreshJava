import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class for the Admin.
 * The class extends User, which is an abstract class with abstract methods: checkLogin, indexOfUser and readFile.
 */
public class Admin extends User{
  ArrayList<String> admins = readFile();

  /**
   * Constructor for Admin.
   *
   * @param username of the admin.
   * @param password of the admin.
   */
  public Admin(String username,String password){
    this.username = username;
    this.password = password;
  }


  /**
   * Method to get the Username of the admin.
   *
   * @return username
   */
  public String getUsername(){
    return username;
  }


  /**
   * Method to read the Admin.dat file.
   *
   * @return admins - an ArrayList of the file.
   */
  public ArrayList<String> readFile(){
    ArrayList<String> admins = new ArrayList<String>();
    try{
      FileReader fr = new FileReader("Admin.dat");
      BufferedReader br = new BufferedReader(fr);
      String str = "";

      while((str = br.readLine()) != null){
        admins.add(str);
      }
      br.close();
    }catch(IOException e){
      e.printStackTrace();
    }
    return admins;
  }


  /**
   * Method to get the index position of the user. (Abstract method in User)
   * Declares a temp String array 'tempAdmin' and then uses regex to split from the comma
   *
   * @return index - the index position of the admin.
   */
  public int indexOfUser(){
    readFile();
    int index = -1;

    for(int i = 0; i < admins.size(); i++){
      String[] tempAdmin = (admins.get(i).split(","));
      if(tempAdmin[0].equalsIgnoreCase(username)){
        index = i;
      }
    }
    return index;
  }


  /**
   * Method to check the Log in of the admin (Abstract method in User).
   *
   * @return valid if the password entered matches the admins password on the Admin.dat file.
   */
  @Override
  public boolean checkLogin(){
    boolean valid;
    readFile();
    String[] temp = admins.get(indexOfUser()).split(",");

    if(temp[1].equalsIgnoreCase(password)){
      valid = true;
    }else{
      valid = false;
    }
    return valid;
  }
}