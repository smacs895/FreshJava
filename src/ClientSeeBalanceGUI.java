import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;


/**  Class for the ClientSeeBalanceGUI.
 *   The class extends ClientScreenGUI.
 */
public class ClientSeeBalanceGUI extends ClientScreenGUI{
  Scene clientSeeBalanceGUI;


  /** Method to run the ClientSeeBalanceGUI
   *
   *  @param primaryStage for the ClientSeeBalanceGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney - 18173837");

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;" + "-fx-font-size: 1em;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);

    //Create new instance of Client.
    Client client = new Client(userName);


    //Needed a try catch block to read in the images. Throws an Exception if the image file is not found.
    FileInputStream input = null;

    try{
      input = new FileInputStream("euros.png");
    }catch(IOException e){
      e.printStackTrace();
    }
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(500);
    imageView.setFitHeight(250);



    //Labels that show the clients credit balance with their name.
    Label label = new Label();
    label.setText(userName + ", your current Credit Balance is: €" + decimalFormat.format(client.getCredit()));
    label.setPrefSize(350,50);
    label.setAlignment(Pos.CENTER);
    label.setFont(new Font("Arial",25));
    label.setStyle(buttonStyles);

    //Main Menu button - Goes to the Main Menu for the client.
    Button button = new Button();
    button.setText("Main Menu");
    ClientScreenGUI clientScreenGUI = new ClientScreenGUI();
    button.setOnAction(e -> {
      clientScreenGUI.start(primaryStage);
    });
    button.setStyle(buttonStyles);
    button.setAlignment(Pos.CENTER);


    //VBox to put the label, image and button into.
    VBox vBox = new VBox();
    vBox.getChildren().addAll(imageView,label,button);
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);

    //Gridpane holds the background image and the VBox.
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene clientSeeBalanceGUI
    clientSeeBalanceGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    clientSeeBalanceGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(clientSeeBalanceGUI);
    //Show the stage.
    primaryStage.show();
  }
}