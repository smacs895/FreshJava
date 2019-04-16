import javafx.application.Application;
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


/**  Class for the welcomeGUI.
 *   The class extends Application.
 */
public class welcomeGUI extends Application{
  Scene welcomeScene;


  /** Method to run the welcomeGUI
   *
   *  @param primaryStage for the welcomeGUI
   */
  @Override
  public void start(Stage primaryStage) throws Exception{
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 3px;" + "-fx-text-size:2em ;" + "-fx-font-size: 1em;";

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    FileInputStream input = new FileInputStream("Vm.png");
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);


    //Label, welcoming you to the Vending Machine and a button ot continut on to the LoginGUI
    Label welcomeLabel = new Label();
    welcomeLabel.setText("Welcome to Stephen's Vending Machine!");
    welcomeLabel.setFont(new Font("Arial",25));


    Button button1 = new Button("Continue");
    LoginGUI logingui = new LoginGUI();
    button1.setOnAction(e -> {
      logingui.start(primaryStage);

    });
    button1.setStyle(buttonStyles);
    button1.setAlignment(Pos.CENTER);


    //VBox to put the image, button and label into.
    VBox vBox = new VBox();
    vBox.setSpacing(10);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(imageView,welcomeLabel,button1);


    //Gridpane holds the background image and the VBox (VBox has all the buttons and the label in it.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene shutDownSystemScreenSceneGUI
    welcomeScene = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    welcomeScene.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(welcomeScene);
    //Show the stage.
    primaryStage.show();
  }
/*
  public static void main(String[]args){
    launch(args);
  }
*/
}
