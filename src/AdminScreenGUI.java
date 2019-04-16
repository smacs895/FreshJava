import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**  Class for the AdminScreenGUI.
 *   The class extends AdminGUI.
 */
public class AdminScreenGUI extends AdminGUI{
  Scene adminSceneGUI;


  /** Method to run the AdminScreenGUI
   *
   *  @param primaryStage for the AdminScreenGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney - 18173837");

    //Styles for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    //Needed a try catch block to read in the image. Throws an Exception if the image file is not found.
    FileInputStream input3 = null;

    try{
      input3 = new FileInputStream("admin2.png");
    }catch(IOException e){
      e.printStackTrace();
    }
    Image image3 = new Image(input3);
    ImageView imageView3 = new ImageView(image3);

    Admin admin = new Admin(userName,passWord);


    //Labels and buttons for the options available to the user.
    Label loggedInAs = new Label();
    loggedInAs.setText("Logged in as: " + admin.getUsername());
    loggedInAs.setFont(new Font("Arial",25));


    Label optionLabel = new Label();
    optionLabel.setText("Please select an option below:");
    optionLabel.setFont(new Font("Arial",25));

    //Buy Products button
    Button addProductButton = new Button("Add New Product");
    AddNewProductGUI addProductGUI = new AddNewProductGUI();
    addProductButton.setOnAction(e -> {
      addProductGUI.start(primaryStage);
    });
    addProductButton.setStyle(buttonStyles);


    //Restock Products button. Goes to the ReStockProductGUI
    Button restockProductButton = new Button("Restock Product");
    RestockProductGUI restockProductGUI = new RestockProductGUI();
    restockProductButton.setOnAction(e -> {
      restockProductGUI.start(primaryStage);
    });
    restockProductButton.setStyle(buttonStyles);


    //See Balance button. Goes to the ShutDownSystemScreenGUI
    Button shutdownSystemButton = new Button("Shutdown System");
    ShutDownSystemScreenGUI shutDownSystemScreenGUI = new ShutDownSystemScreenGUI();
    shutdownSystemButton.setOnAction(e -> {
      shutDownSystemScreenGUI.start(primaryStage);
    });
    shutdownSystemButton.setStyle(buttonStyles);

    //Log Out button. Goes to the LoginGUI
    Button logOutButton = new Button("Log Out");
    LoginGUI loginGUI = new LoginGUI();
    logOutButton.setOnAction(e -> {
      loginGUI.start(primaryStage);
    });
    logOutButton.setStyle(buttonStyles);


    //2 HBoxes and 1 VBox to put all of the labels and buttons into.
    //The 2 HBoxes go into the VBox.
    HBox loggInhBox = new HBox();
    loggInhBox.setAlignment(Pos.CENTER);
    loggInhBox.getChildren().add(loggedInAs);

    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(addProductButton,restockProductButton,shutdownSystemButton,logOutButton);


    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(loggInhBox,imageView3,optionLabel,hBox);

    //Gridpane holds the background image and the VBox (Vbox has the HBoxes in it too.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene adminSceneGUI
    adminSceneGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    adminSceneGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(adminSceneGUI);
    //Show the stage.
    primaryStage.show();
  }
}