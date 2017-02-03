package reservations.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RoomsEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> singleBedsComb;

    @FXML
    private ComboBox<Integer> doubleBedsCombo;

    @FXML
    private CheckBox hasBlanconyChecked;

    @FXML
    private TextField locationsTxt;

    @FXML
    private TextField costTxt;

    @FXML
    private Button addRoom;

    @FXML
    private ComboBox<Integer> roomsComb;
    
    @FXML
    private CheckBox isLuxury;

    @FXML
    private TextField nameTxt;
    
    @FXML
    private CheckBox availCheck;
    
    @FXML
    void addRoomtoDB(ActionEvent event) {
    	
    	if(!nameTxt.getText().isEmpty() && !costTxt.getText().isEmpty()){
    	int luxury = 0;
    	int balcony = 0;
    	int avail = 0;
    	String name = nameTxt.getText();
    	boolean hasBalcony = hasBlanconyChecked.isSelected();if(hasBalcony){balcony=1;}
    	boolean isLuxurious = isLuxury.isSelected();if(isLuxurious){luxury = 1;}
    	boolean isAvail = availCheck.isSelected();if(isAvail){avail = 1;}
    	Float cost = Float.valueOf(costTxt.getText());
    	//String location = locationsTxt.getText();
    	int singleBeds = Integer.valueOf(singleBedsComb.getSelectionModel().getSelectedItem());
    	int doubleBeds = Integer.valueOf(doubleBedsCombo.getSelectionModel().getSelectedItem());
    	int numRooms = Integer.valueOf(roomsComb.getSelectionModel().getSelectedItem());
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Room Creation");
    	alert.setHeaderText(null);
    	alert.setContentText("Your Room Creation was Succesfull!");
    	alert.showAndWait();
    	
    	RoomManagement room = new RoomManagement(name, 0f, cost, numRooms, singleBeds, doubleBeds, balcony, 1, luxury, 0, 0, 0, false, avail);
    	room.addRoom();
    	}else{
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Room Creation");
        	alert.setHeaderText(null);
        	alert.setContentText("Room Creation Failed.\nPlease Fill all information fields.");
        	alert.showAndWait();
    	}
    }

    @FXML
    void initialize() {
    	singleBedsComb.getItems().addAll(
    			1,
    			2,
    			3
    			);
    	singleBedsComb.getSelectionModel().select(0);
    	doubleBedsCombo.getItems().addAll(
    			1,
    			2,
    			3
    			);
    	doubleBedsCombo.getSelectionModel().select(0);
    	roomsComb.getItems().addAll(
    			1,
    			2,
    			3,
    			4,
    			5
    			);
    	roomsComb.getSelectionModel().select(0);
    }
}
