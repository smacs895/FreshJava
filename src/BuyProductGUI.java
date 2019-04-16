import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**  Class for the BuyProductGUI.
 *   The class extends ClientScreenGUI.
 */
public class BuyProductGUI extends ClientScreenGUI{


  /** Method to run the BuyProductGUI
   *
   *  @param primaryStage for the BuyProductGUI
   */
  @Override
  public void start(Stage primaryStage){
    //Set the title of the primary stage.
    primaryStage.setTitle("Stephen Mac Sweeney - 18173837");

    //Style for buttons.
    String buttonStyles = "-fx-border-color: #228B22; -fx-border-width: 2px;" + "-fx-text-size:2em ;"
        + "-fx-font-size: 1em;";

    //Format for the price and credit balance to show with 2 decimal places.
    DecimalFormat df = new DecimalFormat("#.00");

    //Insert a background image.
    Image backgroundImage = new Image((new File("background.png").toURI().toString()));
    BackgroundImage myBackgroundImage = new BackgroundImage((backgroundImage),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);


    //Crete new instances of Client and Vending Machine.
    Client client = new Client(userName);
    VendingMachine vend = new VendingMachine();

    //Create arrayList of products, which reads the products from the method in Vending machine.
    ArrayList<Product> products = vend.readProduct();

    //Create a string array of buttons, which is set at maximum size of 20.
    //These buttons will represent each product that is in the vending machine.
    //If there is less than 20 products on the Product.dat file, the remaining buttons will display "Available Slot"
    Button[] buttons = new Button[20];
    for(int i = 0; i < buttons.length; i++){
      buttons[i] = new Button("Available Slot");
    }

    //Setting the style on the buttons and displaying the description, price and stock level of the product.
    for(int i = 0; i < products.size(); i++){
      buttons[i] = new Button(products.get(i).getDescription() + "\n€" + df.format(products.get(i).getPrice())
          + "\nStock:" + products.get(i).getQuantity());
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


    //When a button is pressed, this checks that there is stock of it and also that the client has enough credit
    // to purchase that product. Pop up messages display to alert if its bought or if there is no stock or not enough credit.
    for(int i = 0; i < buttons.length; i++){
      int j = i;
      buttons[i].setOnAction(e -> {
        if(products.get(j).getQuantity() >= 1){
          if(client.getCredit() >= products.get(j).getPrice()){
            vend.buyProduct(products.get(j).getDescription());
            client.setCredit(client.getCredit() - vend.products.get(j).getPrice());
            client.writeClient();

            Alert VendedAlert = new Alert(Alert.AlertType.INFORMATION);
            VendedAlert.setTitle(products.get(j).getDescription() + " is being vended!");
            VendedAlert.setHeaderText(null);
            VendedAlert.setContentText("Enjoy your " + products.get(j).getDescription() + " " + userName
                + "! \nYour credit is now: €" + df.format(client.getCredit()));
            VendedAlert.showAndWait();
            BuyProductGUI buyProductGUI = new BuyProductGUI();
            buyProductGUI.start(primaryStage);
          }else{
            Alert lowCreditAlert = new Alert(Alert.AlertType.INFORMATION);
            lowCreditAlert.setTitle("Not enough Credit!");
            lowCreditAlert.setHeaderText(null);
            lowCreditAlert.setContentText(userName + ", your credit balance is €" + df.format(client.getCredit())
                + ".\nPlease top up using the app!");
            lowCreditAlert.showAndWait();
          }
        }else{
          Alert lowStockAlert = new Alert(Alert.AlertType.INFORMATION);
          lowStockAlert.setTitle("Out of stock!");
          lowStockAlert.setHeaderText(null);
          lowStockAlert.setContentText(userName + ", we are currently out of stock of " + products.get(j).getDescription()
              + "\nPlease try another selection!");
          lowStockAlert.showAndWait();
        }
      });
    }


    //Labels displaying the users name, their credit balance and prompt to select a product or return to main menu for client.
    Label title1 = new Label("Hello " + userName + "!");
    title1.setPrefSize(600,15);
    title1.setAlignment(Pos.CENTER);
    title1.setFont(new Font("Arial",25));

    Label title2 = new Label("Your current Credit Balance is: €" + df.format(client.getCredit()));
    title2.setPrefSize(350,15);
    title2.setAlignment(Pos.CENTER);
    title2.setFont(new Font("Arial",25));
    title2.setStyle(buttonStyles);

    Label title3 = new Label("Select a product to purchase or return to the Main Menu");
    title3.setPrefSize(700,15);
    title3.setAlignment(Pos.CENTER);
    title3.setFont(new Font("Arial",15));

    //Button to return to the main menu for the client.
    Button button = new Button();
    button.setText("Main Menu");
    ClientScreenGUI clientScreenGUI = new ClientScreenGUI();
    button.setOnAction(e -> {
      clientScreenGUI.start(primaryStage);
    });
    button.setStyle(buttonStyles);
    button.setAlignment(Pos.CENTER);


    //Needed a try catch block to read in the images. Throws an Exception if the image file is not found.
    FileInputStream input = null;
    FileInputStream input2 = null;
    try{
      input = new FileInputStream("Monster.png");
      input2 = new FileInputStream("CocaCola.png");
    }catch(IOException e){
      e.printStackTrace();
    }

    Image image = new Image(input);
    ImageView imageView = new ImageView(image);

    Image image2 = new Image(input2);
    ImageView imageView2 = new ImageView(image2);


    //HBox and 2 VBoxes to put all of the labels, buttons and images into.
    //The 2 HBoxes go into the VBox.
    HBox hBox = new HBox();
    hBox.getChildren().addAll(imageView,button,imageView2);
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(20);


    VBox vBox2 = new VBox();
    vBox2.setAlignment(Pos.CENTER);
    vBox2.getChildren().addAll(title1,title2,title3);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(vBox2,hBox);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPrefWidth(890);
    vBox.setSpacing(10.0);

    flowPane.getChildren().add(vBox);


    //Gridpane holds the background image and the flowpane (flowpane has all the buttons and the vBox in it.)
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(12,13,14,15));
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setBackground(new Background(myBackgroundImage));
    gridPane.add(flowPane,0,0);

    //Declare the scene BuyProductScene
    Scene BuyProductScene = new Scene(gridPane,900,600);
    //Set the mouse cursor to a hand. I tried a Batman Logo for Ashish but ran into some visibility problems :)
    BuyProductScene.setCursor(Cursor.HAND);

    //Set the scene.
    primaryStage.setScene(BuyProductScene);
    //Show the stage.
    primaryStage.show();
  }
}