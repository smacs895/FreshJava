import java.util.ArrayList;

/** Abstract class for users - admins and clients.
 *
 */
public abstract class User{
   protected static String username;
   protected static String password;



  /** Default Constructor
   *
   */
  public User(){
  }



  /** Overloaded Constructor
   *
   * @param username of the user
   * @param password of the user
   */
  public User(String username,String password){
    this.username = username;
    this.password = password;
  }


  /** Abstract method to check the log in details (username and password)
   *
   * @return
   */
  public abstract boolean checkLogin();



  /** Abstract method to read the Client.dat file.
   *
   * @return
   */
  public abstract ArrayList<String> readFile();



  /** Abstract method to find the index position of the user in the respective Client.dat and Admin.dat files.
   *
   * @return
   */
  public abstract int indexOfUser();
}