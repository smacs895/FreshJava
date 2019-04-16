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


/**  Class for the AdminGUI.
 *   The class extends the LoginGUI
 */
public class AdminGUI extends LoginGUI{
  private Scene AdminScene;
  protected static String userName = "";
  protected static String passWord = "";


  /** Method to run the AdminGUI
   *
   *  @param primaryStage for the AdminGUI
   */
  @Override
  public void start(Stage primaryStage) {
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Styles for buttons and text fields.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";
    String fieldStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    //Needed a try catch block to read in the image. Throws an Exception if the image file is not found.
    FileInputStream input = null;

    try{
      input = new FileInputStream("Admin.png");
    }
      catch(IOException e){
      e.printStackTrace();
      }
    Image image4 = new Image(input);
    ImageView imageView4 = new ImageView(image4);


    //Labels, text fields and password fields for the log in details for the admin.
    Label adminLogInLabel = new Label("Admin Log-In");
    adminLogInLabel.setFont(new Font("Arial",25));

    Label userLabel2 = new Label("Username:");
    TextField userTextField2 = new TextField();
    userTextField2.setStyle(fieldStyles);
    userTextField2.setPromptText("Enter your username");

    Label passLabel2 = new Label("Password:");
    PasswordField passwordField2 = new PasswordField();
    passwordField2.setStyle(fieldStyles);
    passwordField2.setPromptText("Enter your password");


    //Button to log the admin in, with setOnAction implemented.
    //There are 3 outcomes, 1)The admin is logged in if password and username match.
    //                      2)Pop up message that the username is incorrect.
    //                      3)Pop up message that the password is incorrect.
    Button adminLogInButton = new Button("Log-In");
    adminLogInButton.setOnAction(e -> {
      AdminScreenGUI adminScreenGUI = new AdminScreenGUI();
      userName = userTextField2.getText();
      passWord = passwordField2.getText();
      Admin admin = new Admin(userName,passWord);

      if(admin.indexOfUser() == -1){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Username!");
        alert.showAndWait();
      }else{
        if(admin.checkLogin()){
          adminScreenGUI.start(primaryStage);
        }else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Incorrect Password!");
          alert.showAndWait();
        }
      }
    });
    adminLogInButton.setStyle(buttonStyles);
    adminLogInButton.setAlignment(Pos.CENTER);


    //HBox and VBox to put all of the labels, text fields, password fields and buttons into.
    //The HBox goes into the VBox.
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(userLabel2,userTextField2,passLabel2,passwordField2);

    VBox vBox = new VBox();
    vBox.setSpacing(7.5);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(imageView4,adminLogInLabel,hBox,adminLogInButton);


    //Gridpane holds the background image and the VBox (Vbox has the HBox in it too.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);

    //Declare the scene AdminScene
    AdminScene = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    AdminScene.setCursor(Cursor.HAND);

    //Set the Scene.
    primaryStage.setScene(AdminScene);
    //Show the stage.
    primaryStage.show();
  }
}