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


/**  Class for the ClientScreenGUI.
 *   The class extends ClientGUI.
 */
public class ClientScreenGUI extends ClientGUI{
  Scene clientSceneGUI;


  /** Method to run the ClientScreenGUI
   *
   *  @param primaryStage for the ClientScreenGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney - 18173837");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";

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

    //Create new instance of Client.
    Client client = new Client(userName);


    //Labels that show the clients user name and prompt to select an option.
    Label loggedInAs = new Label();
    loggedInAs.setText("Logged in as: " + client.getUsername());
    loggedInAs.setFont(new Font("Arial",25));


    Label optionLabel = new Label();
    optionLabel.setText("Please select an option below:");
    optionLabel.setFont(new Font("Arial",25));

    //Buy Products button - Goes to the BuyProductGUI
    Button buyProductsButton = new Button("Buy Products");
    BuyProductGUI buyProductGUI = new BuyProductGUI();
    buyProductsButton.setOnAction(e -> {
      buyProductGUI.start(primaryStage);
    });
    buyProductsButton.setStyle(buttonStyles);

    //See Balance button - Goes to the ClientSeeBalanceGUI
    Button seeBalanceButton = new Button("See Balance");
    ClientSeeBalanceGUI clientSeeBalanceGUI = new ClientSeeBalanceGUI();
    seeBalanceButton.setOnAction(e -> {
      clientSeeBalanceGUI.start(primaryStage);
    });
    seeBalanceButton.setStyle(buttonStyles);

    //Log Out button - Goes to the LoginGUI
    Button logOutButton = new Button("Log Out");
    LoginGUI loginGUI = new LoginGUI();
    logOutButton.setOnAction(e -> {
      loginGUI.start(primaryStage);
    });
    logOutButton.setStyle(buttonStyles);


    //2 HBoxes and VBox to put all of the labels, images and buttons into.
    //The HBox goes into the VBox.
    HBox loggInhBox = new HBox();
    loggInhBox.setAlignment(Pos.CENTER);
    loggInhBox.getChildren().add(loggedInAs);

    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(buyProductsButton,seeBalanceButton,logOutButton);

    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(loggInhBox,imageView3,optionLabel,hBox);

    //Gridpane holds the background image and the VBox (Vbox has the 2 HBoxes in it too.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene clientSceneGUI
    clientSceneGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    clientSceneGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(clientSceneGUI);
    //Show the stage.
    primaryStage.show();
  }
}