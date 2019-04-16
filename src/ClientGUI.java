import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**  Class for the ClientGUI.
 *   The class extends LoginGUI.
 */
public class ClientGUI extends LoginGUI{
  Scene ClientScene;
  protected static String userName = "";
  protected static String passWord = "";


  /** Method to run the ClientGUI
   *
   *  @param primaryStage for the ClientGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Style for buttons and fields.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";
    String fieldStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    //Needed a try catch block to read in the images. Throws an Exception if the image file is not found.
    FileInputStream input3 = null;

    try{
      input3 = new FileInputStream("Client.png");
    }catch(IOException e){
      e.printStackTrace();
    }
    Image image3 = new Image(input3);

    ImageView imageView3 = new ImageView(image3);


    //Labels, text fields and password fields for the log in details for the admin.
    Label clientLogInLabel = new Label("Client Log-In");
    clientLogInLabel.setFont(new Font("Arial",25));

    Label userLabel1 = new Label("Username:");
    TextField userTextField1 = new TextField();
    userTextField1.setStyle(fieldStyles);
    userTextField1.setPromptText("Enter your username");

    Label passLabel1 = new Label("Password:");
    PasswordField passwordField1 = new PasswordField();
    passwordField1.setStyle(fieldStyles);
    passwordField1.setPromptText("Enter your password");

    //Button to log the client in, with setOnAction implemented.
    //There are 3 outcomes, 1)The client is logged in if password and username match.
    //                      2)Pop up message that the username is incorrect.
    //                      3)Pop up message that the password is incorrect.
    Button clientLogInButton = new Button("Log-In");
    clientLogInButton.setOnAction(e ->
    {
      ClientScreenGUI clientScreen = new ClientScreenGUI();
      userName = userTextField1.getText();
      passWord = passwordField1.getText();
      Client client = new Client(userName,passWord);

      if(client.indexOfUser() == -1){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Username!");
        alert.showAndWait();
      }else{
        if(client.checkLogin()){
          clientScreen.start(primaryStage);
        }else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Incorrect Password!");
          alert.showAndWait();
        }
      }
    });
    clientLogInButton.setStyle(buttonStyles);
    clientLogInButton.setAlignment(Pos.CENTER);

    //HBox and VBox to put all of the labels, text fields, password fields and buttons into.
    //The HBox goes into the VBox.
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(userLabel1,userTextField1,passLabel1,passwordField1);

    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(imageView3,clientLogInLabel,hBox,clientLogInButton);

    //Gridpane holds the background image and the VBox (Vbox has the HBox in it too.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene ClientScene
    ClientScene = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    ClientScene.setCursor(Cursor.HAND);

    //Set the scene
    primaryStage.setScene(ClientScene);
    //Show the stage
    primaryStage.show();
  }
}