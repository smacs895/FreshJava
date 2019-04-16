import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/** Class for the AddNewProductGUI.
 *  The class extends BuyProductGUI.
 *
 */
public class AddNewProductGUI extends BuyProductGUI{
  private Scene addProductGUI;
  private String description;
  private String location;
  private double price;
  private int quantity;


  /** Method to run the AddNewProductGUI
   *
   *  @param primaryStage for the AddNewProductGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney 18173837");

    //Create new instance of VendingMachine.
    VendingMachine vend = new VendingMachine();

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
      input = new FileInputStream("newproduct.png");
    }catch(IOException e){
      e.printStackTrace();
    }
    Image image = new Image(input);
    ImageView imageView = new ImageView(image);


    //Labels and text fields for the Description, Location, Price and Quantity of the product.
    Label userLabel = new Label("Description:");
    TextField textField = new TextField();
    textField.setPromptText("Enter the description:");
    textField.setStyle(fieldStyles);

    Label userLabel2 = new Label("Location:");
    TextField textField2 = new TextField();
    textField2.setPromptText("Enter the location:");
    textField2.setStyle(fieldStyles);

    Label userLabel3 = new Label("Price:");
    TextField textField3 = new TextField();
    textField3.setPromptText("Enter the price:");
    textField3.setStyle(fieldStyles);

    Label userLabel4 = new Label("Quantity:");
    TextField textField4 = new TextField();
    textField4.setPromptText("Enter the quantity:");
    textField4.setStyle(fieldStyles);


    //Button to Add the new product, with setOnAction implemented.
    //There are 2 outcomes, 1)The new product is added or 2)There are no slots available for the new product.
    Button addProductButton = new Button("Add New Product");
    addProductButton.setStyle(buttonStyles);
    addProductButton.setAlignment(Pos.CENTER);
    addProductButton.setOnAction(e -> {

      Product productToBeAdded = new Product(description = textField.getText(),location = textField2.getText(),
          price = Double.parseDouble(textField3.getText()),quantity = Integer.parseInt(textField4.getText()));


      if(vend.readButtons().size() >= 20){
        Alert VendedAlert = new Alert(Alert.AlertType.INFORMATION);
        VendedAlert.setTitle("Cannot add product");
        VendedAlert.setHeaderText(null);
        VendedAlert.setContentText("All of the available slots in the Vending Machine have been taken.");
        VendedAlert.showAndWait();
      }else if(vend.readButtons().size() >= 19){
        Alert productAddedAlert = new Alert(Alert.AlertType.INFORMATION);
        productAddedAlert.setTitle("Product Added");
        productAddedAlert.setHeaderText(null);
        productAddedAlert.setContentText(productToBeAdded.getQuantity() + " " + productToBeAdded.getDescription() + " was added to the Vending Machine!");
        productAddedAlert.showAndWait();
        vend.addNewProduct(productToBeAdded);
      }
    });


    //Button to return to the Admin Main Menu page.
    Button cancelButton = new Button("Main Menu");
    AdminScreenGUI adminScreenGUI = new AdminScreenGUI();
    cancelButton.setStyle(buttonStyles);
    cancelButton.setAlignment(Pos.CENTER);
    cancelButton.setOnAction(e -> {
      adminScreenGUI.start(primaryStage);
    });


    //3 HBoxes and 1 VBox to display all of the labels, text fields, buttons and images.
    //All of the HBoxes go into the VBox.
    HBox hBox = new HBox();
    hBox.setSpacing(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(userLabel,textField,userLabel2,textField2);

    HBox hBox2 = new HBox();
    hBox2.setSpacing(5);
    hBox2.setAlignment(Pos.CENTER);
    hBox2.getChildren().addAll(userLabel3,textField3,userLabel4,textField4);

    HBox hBox3 = new HBox();
    hBox3.setSpacing(10);
    hBox3.setAlignment(Pos.CENTER);
    hBox3.getChildren().addAll(addProductButton,cancelButton);


    VBox vBox = new VBox();
    vBox.setSpacing(5);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(imageView,hBox,hBox2,hBox3);


    //Gridpane holds the background image and the VBox (Vbox has all of the HBoxes in it.)
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(12,13,14,15));
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setBackground(new Background(myBackgroundImage));
    pane.add(vBox,0,0);


    //Declare the scene addProductGUI
    addProductGUI = new Scene(pane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    addProductGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(addProductGUI);
    //Show the stage.
    primaryStage.show();
  }
}