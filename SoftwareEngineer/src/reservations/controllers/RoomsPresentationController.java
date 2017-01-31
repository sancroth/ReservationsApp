package reservations.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reservations.Main;

public class RoomsPresentationController {

	
	private BorderPane rootlayOut;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button checkForRoomBtn;

    @FXML
    private Button hrManagementBtn;

    @FXML
    private Button roomsManagementBtn;
    
    @FXML
    private ImageView topBg;
    
    
    
    @FXML
    private void checkForRooms(ActionEvent event) {
        Stage dialog = new Stage();
        dialog = loadNewScene(dialog);
        Scene scene = new Scene(rootlayOut);
        dialog.setScene(scene);
        loadStage("RoomsReservation.fxml");
        dialog.getIcons().add(new Image("file:image/vacations.png"));
        dialog.setTitle("Room Reservation App");
        dialog.show();  
    }

    @FXML
    private void hrManagementLunch(ActionEvent event) {
        Stage dialog = new Stage();
        dialog = loadNewScene(dialog);
        Scene scene = new Scene(rootlayOut);
        dialog.setScene(scene);
        loadStage("HRManagement.fxml");
        dialog.getIcons().add(new Image("file:image/hr.png"));
        dialog.setTitle("Human Resource Management");
        dialog.show();    
    }


    @FXML
    private void roomsManagement(ActionEvent event) {
        Stage dialog = new Stage();
        dialog = loadNewScene(dialog);
        Scene scene = new Scene(rootlayOut);
        dialog.setScene(scene);
        loadStage("RoomsManagement.fxml");
        dialog.getIcons().add(new Image("file:image/rent-hello-logo.png"));
        dialog.setTitle("Room Reservation App");
        dialog.show();   
    }
    
    @FXML
    void popUpLogin(ActionEvent event) {
    	//System.out.println(event);
        Stage dialog = new Stage();
        dialog = loadNewScene(dialog);
        Scene scene = new Scene(rootlayOut);
        dialog.setScene(scene);
        loadStage("Login.fxml");
        dialog.getIcons().add(new Image("file:image/key.png"));
        dialog.setTitle("User Login");
        dialog.show();     
    }
    
    private Stage loadNewScene(Stage dialog){
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getPrimaryStage());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
        try {
			rootlayOut = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dialog;	
    }
    
    private void loadStage(String maybeFxmlPath){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/"+maybeFxmlPath));
			AnchorPane login = (AnchorPane) loader.load();
	    	login.minWidthProperty().bind(rootlayOut.widthProperty());
	    	login.minHeightProperty().bind(rootlayOut.heightProperty());
		    rootlayOut.setCenter(login);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void initialize() {
    	Image vacationsImage = new Image("rent-hello-logo.png");
    	topBg.setImage(vacationsImage);
    }
}
