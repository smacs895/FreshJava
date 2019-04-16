import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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


/**  Class for the ShutDownSystemScreenGUI.
 *   The class extends AdminScreenGUI.
 */
public class ShutDownSystemScreenGUI extends AdminScreenGUI{
  Scene shutDownSystemScreenSceneGUI;


  /** Method to run the ShutDownSystemScreenGUI
   *
   *  @param primaryStage for the ShutDownSystemScreenGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;" + "-fx-font-size: 1em;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    //Needed a try catch block to read in the images. Throws an Exception if the image file is not found.
    FileInputStream input = null;
    try{
      input = new FileInputStream("shutdown.png");
    }
    catch(IOException e){
      e.printStackTrace();
    }
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);



    //Label to prompt the admin if they're sure they want to shut the system down for the night.
    Label label = new Label();
    label.setText("Are you sure you want to shut the system down for the night " + userName + "?");
    label.setFont(new Font("Arial",25));


    //Button shut the system down for the night.
    //A pop up shows confirmation of the system being shut down, then the program closes.
    Button shutDownButton = new Button();
    shutDownButton.setGraphic(new ImageView(image));
    AdminScreenGUI adminScreenGUI = new AdminScreenGUI();
    shutDownButton.setOnAction(e -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Thank you for using Stephen's Vending Machine");
      alert.setHeaderText(null);
      alert.setContentText("The system will now be shut down.\n Have a great night!");
      alert.showAndWait();
      Platform.exit();
    });
    shutDownButton.setAlignment(Pos.CENTER);


    //Button to return to the main menu for the admin.
    Button cancelButton = new Button("Cancel");
    //AdminScreenGUI adminScreenGUI = new AdminScreenGUI();  // Uncomment this when finish setting up the button above.
    cancelButton.setOnAction(e -> {
      adminScreenGUI.start(primaryStage);

    });
    cancelButton.setStyle(buttonStyles);
    cancelButton.setAlignment(Pos.CENTER);


    //VBox to put all of the buttons and label into.
    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(label,shutDownButton,cancelButton);


    //Gridpane holds the background image and the VBox (VBox has all the buttons and the label in it.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene shutDownSystemScreenSceneGUI
    shutDownSystemScreenSceneGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    shutDownSystemScreenSceneGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(shutDownSystemScreenSceneGUI);
    //Show the stage.
    primaryStage.show();
  }
}