import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**  Class for the LoginGUI.
 *   The class extends welcomeGUI.
 */
public class LoginGUI extends welcomeGUI{
  Scene LoginGUI;


  /** Method to run the LoginGUI
   *
   *  @param primaryStage for the LoginGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    //Needed a try catch block to read in the images. Throws an Exception if the image file is not found.
    FileInputStream input2 = null;

    try{
      input2 = new FileInputStream("login.png");
    }catch(IOException e){
      e.printStackTrace();
    }

    Image image2 = new Image(input2);
    ImageView imageView2 = new ImageView(image2);



    //Labels that prompts the user f they want to login as an admin or client.
    Label label2 = new Label("Would you like to Log in as a Client or as an Admin?");
    label2.setFont(new Font("Arial",25));

    //Client button - Goes to the ClientGUI
    Button clientButton = new Button("Client");
    ClientGUI clientGUI = new ClientGUI();
    clientButton.setStyle(buttonStyles);
    clientButton.setOnAction(e -> {
      primaryStage.setScene(LoginGUI);
      clientGUI.start(primaryStage);
    });

    //Admin button - Goes to the AdminGUI
    Button adminButton = new Button("Admin");
    AdminGUI adminGUI = new AdminGUI();
    adminButton.setStyle(buttonStyles);
    adminButton.setOnAction(e -> {
      primaryStage.setScene(LoginGUI);
      adminGUI.start(primaryStage);
    });


    //HBox and VBox to put all of the labels, images and buttons into.
    //The HBox goes into the VBox.
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(clientButton,adminButton);

    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(imageView2,label2,hBox);


    //Gridpane holds the background image and the VBox (Vbox has the HBox in it too.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene clientSceneGUI
    LoginGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    LoginGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(LoginGUI);
    //Show the stage.
    primaryStage.show();
  }
}