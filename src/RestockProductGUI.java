import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


/**  Class for the RestockProductGUI.
 *   The class extends BuyProductGUI.
 */
public class RestockProductGUI extends BuyProductGUI{
  Scene restockProductGUI;


  /** Method to run the RestockProductGUI
   *
   *  @param primaryStage for the RestockProductGUI
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

    //Create new instance of Vending Machine and Admin.
    VendingMachine vend = new VendingMachine();
    Admin admin = new Admin(userName,passWord);

    //ArrayList reads the Product.dat file.
    ArrayList<Product> products = vend.readProduct();




    //This shows the buttons on scrren in the flow pane the same way as it does in the BuyProductGUI.
    Button[] buttons = new Button[20];
    for(int i = 0; i < buttons.length; i++){
      buttons[i] = new Button("Available Slot");
    }

    for(int i = 0; i < products.size(); i++){
      buttons[i] = new Button(products.get(i).getDescription() + "\nStock:" + products.get(i).getQuantity());
      buttons[i].setStyle(buttonStyles);
      buttons[i].setTextAlignment(TextAlignment.CENTER);
    }


    //Put the buttons onto a flowpane.
    FlowPane flowPane = new FlowPane();
    flowPane.setAlignment(Pos.TOP_CENTER);
    for(int i = 0; i < buttons.length; i++){
      flowPane.getChildren().add(buttons[i]);
    }

    flowPane.setHgap(5);
    flowPane.setVgap(5);
    flowPane.setAlignment(Pos.CENTER);


    //When a button is pressed, this checks if there is stock of the selected product.
    // To restock the product, the products quantity must be 0. Maximum stock is 10.
    // Pop up messages display to alert if the product is fully stocked (10), if the product has been restocked or if
    // the product still has some stock and cannot be restocked at that time..
    for(int i = 0; i < buttons.length; i++){
      int j = i;
      buttons[i].setOnAction(e -> {

            if(products.get(j).getQuantity() == 0){
              products.get(j).setQuantity(products.get(j).getQuantity() + 10);
              vend.restockProduct(products.get(j).getDescription());
              Alert restockedAlert = new Alert(Alert.AlertType.INFORMATION);
              restockedAlert.setTitle("Product Restocked");
              restockedAlert.setHeaderText(null);
              restockedAlert.setContentText(products.get(j).getDescription() + " has been restocked.");
              restockedAlert.showAndWait();
              RestockProductGUI restockProductGUI = new RestockProductGUI();
              restockProductGUI.start(primaryStage);
            }else if(products.get(j).getQuantity() == 10){
              Alert fullStockAlert = new Alert(Alert.AlertType.INFORMATION);
              fullStockAlert.setTitle("Full Stock");
              fullStockAlert.setHeaderText(null);
              fullStockAlert.setContentText(products.get(j).getDescription() + " is already fully stocked!");
              fullStockAlert.showAndWait();
              RestockProductGUI restockProductGUI = new RestockProductGUI();
              restockProductGUI.start(primaryStage);
            }else if(products.get(j).getQuantity() >= 1){
              Alert fullStockAlert = new Alert(Alert.AlertType.INFORMATION);
              fullStockAlert.setTitle("Some Stock");
              fullStockAlert.setHeaderText(null);
              fullStockAlert.setContentText(products.get(j).getDescription() + " has some stock!"
                  + "\nCannot be restocked until quantity is 0 in stock.");
              fullStockAlert.showAndWait();
              RestockProductGUI restockProductGUI = new RestockProductGUI();
              restockProductGUI.start(primaryStage);
            }
          }
      );
    }


    //Button to return to the main menu for the admin.
    Button mainMenuButton = new Button("Main Menu");
    AdminScreenGUI adminScreenGUI = new AdminScreenGUI();
    mainMenuButton.setStyle(buttonStyles);
    mainMenuButton.setAlignment(Pos.CENTER);
    mainMenuButton.setPrefSize(100,80);

    mainMenuButton.setOnAction(e -> {
      adminScreenGUI.start(primaryStage);
    });


    //HBox and VBox to put all of the labels, buttons and images into.
    //The HBox go into the VBox.
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(mainMenuButton);


    VBox vBox = new VBox();
    vBox.setSpacing(5);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPrefWidth(890);
    vBox.getChildren().addAll(hBox);


    flowPane.getChildren().add(vBox);


    //Gridpane holds the background image and the flowpane (flowpane has all the buttons and the vBox in it.)
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(12,13,14,15));
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setBackground(new Background(myBackgroundImage));
    gridPane.add(flowPane,0,0);


    //Declare the scene RestockProductsScene
    restockProductGUI = new Scene(gridPane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    restockProductGUI.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(restockProductGUI);
    //Show the stage.
    primaryStage.show();
  }
}